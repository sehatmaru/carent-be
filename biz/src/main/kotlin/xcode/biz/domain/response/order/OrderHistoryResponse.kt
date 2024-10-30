package xcode.biz.domain.response.order

import xcode.biz.enums.OrderStatus
import xcode.biz.enums.PaymentStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import java.util.Date

class OrderHistoryResponse {
    var id: Int? = null
    var customerName = ""
    var productName = ""
    var duration = 0
    var transmission: Transmission? = null
    var brand: VehicleBrand? = null
    var totalPayment: Int? = null
    var startDate: Date? = null
    var endDate: Date? = null
    var orderStatus: OrderStatus? = null
    var paymentStatus: PaymentStatus? = null
    var orderedDate: Date? = null
}
