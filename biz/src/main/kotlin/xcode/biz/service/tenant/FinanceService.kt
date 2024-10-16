package xcode.biz.service.tenant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.BillMapper
import xcode.biz.domain.mapper.OrderMapper
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.finance.BalanceReportHistory
import xcode.biz.domain.response.finance.BalanceReportResponse
import xcode.biz.domain.response.finance.BalanceResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.UNAUTHORIZED
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Service
class FinanceService @Autowired constructor(
    private val orderMapper: OrderMapper,
    private val billMapper: BillMapper,
) {

    fun getBalance(): BaseResponse<BalanceResponse> {
        val result = BaseResponse<BalanceResponse>()
        checkPermission()

        val response = BalanceResponse()
        val companyId = CurrentUser.get().companyId!!
        response.totalOrder = orderMapper.countOrder(companyId) ?: 0
        response.totalCustomer = orderMapper.countUniqueCustomer(companyId) ?: 0
        response.totalIncome = billMapper.getTotalIncome(companyId) ?: 0.0
        response.totalRevenue = response.totalIncome - (billMapper.getTotalApplicationFee(companyId) ?: 0.0)

        result.setSuccess(response)

        return result
    }

    fun getBalanceReport(): BaseResponse<BalanceReportResponse> {
        val result = BaseResponse<BalanceReportResponse>()
        checkPermission()

        val response = BalanceReportResponse()
        val companyId = CurrentUser.get().companyId!!
        response.currentOrder = orderMapper.countCurrentMonthOrder(companyId)
        response.currentCustomer = orderMapper.countCurrentMonthUniqueCustomer(companyId)
        response.currentIncome = billMapper.getCurrentMonthTotalIncome(companyId) ?: 0.0
        response.currentRevenue = response.currentIncome - (billMapper.getCurrentMonthTotalApplicationFee(companyId) ?: 0.0)

        val currentDate = LocalDate.now()
        for (i in 6 downTo 0) {
            val previousDate = currentDate.minusMonths(i.toLong())
            val month = previousDate.monthValue
            val year = previousDate.year

            val orderHistory = BalanceReportHistory(
                previousDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase(),
                orderMapper.countOrderHistory(companyId, month, year).toDouble(),
            )

            val customerHistory = BalanceReportHistory(
                previousDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase(),
                orderMapper.countUniqueCustomerHistory(companyId, month, year).toDouble(),
            )

            val incomeHistory = BalanceReportHistory(
                previousDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase(),
                billMapper.getTotalIncomeHistory(companyId, month, year) ?: 0.0,
            )

            val revenueHistory = BalanceReportHistory(
                previousDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase(),
                incomeHistory.value - (billMapper.getTotalApplicationFeeHistory(companyId, month, year) ?: 0.0),
            )

            response.orderHistory.add(orderHistory)
            response.customerHistory.add(customerHistory)
            response.incomeHistory.add(incomeHistory)
            response.revenueHistory.add(revenueHistory)
        }

        result.setSuccess(response)

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
