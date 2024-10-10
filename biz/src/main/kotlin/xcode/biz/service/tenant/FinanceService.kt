package xcode.biz.service.tenant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.repository.BillRepository
import xcode.biz.domain.repository.OrderRepository
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.finance.BalanceResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.UNAUTHORIZED

@Service
class FinanceService @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val billRepository: BillRepository
) {

    fun getBalance(): BaseResponse<BalanceResponse> {
        val result = BaseResponse<BalanceResponse>()
        checkPermission()

        val response = BalanceResponse()
        val companyId = CurrentUser.get().companyId!!
        response.totalOrder = orderRepository.countOrder(companyId) ?: 0
        response.totalCustomer = orderRepository.countUniqueCustomer(companyId) ?: 0
        response.totalIncome = billRepository.getTotalIncome(companyId) ?: 0.0
        response.totalRevenue = response.totalIncome - (billRepository.getTotalApplicationFee(companyId) ?: 0.0)

        result.setSuccess(response)

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
