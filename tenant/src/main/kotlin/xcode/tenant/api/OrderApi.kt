package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.order.OrderHistoryResponse
import xcode.biz.service.tenant.OrderService

@RestController
@RequestMapping(value = ["order"])
class OrderApi @Autowired constructor(
    private val orderService: OrderService,
) {

    @GetMapping("/dashboard/history")
    fun getProvinces(): BaseResponse<List<OrderHistoryResponse>> {
        return orderService.getDashboardOrderHistoryList()
    }
}
