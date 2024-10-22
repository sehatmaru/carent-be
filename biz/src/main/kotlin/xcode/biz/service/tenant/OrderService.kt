package xcode.biz.service.tenant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.OrderMapper
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.order.OrderHistoryResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.UNAUTHORIZED

@Service
class OrderService @Autowired constructor(
    private val orderMapper: OrderMapper,
) {

    fun getDashboardOrderHistoryList(): BaseResponse<List<OrderHistoryResponse>> {
        val result = BaseResponse<List<OrderHistoryResponse>>()

        result.setSuccess(orderMapper.getDashboardOrderHistory(CurrentUser.get().companyId!!))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
