package xcode.biz.domain.response.order

import xcode.biz.enums.OrderStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType
import java.util.Date
import xcode.biz.enums.PaymentStatus

class OrderHistoryResponse {
    var id: Int? = null
    var customerName = ""
    var productName = ""
    var duration = 0
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var brand: VehicleBrand? = null
    var totalPayment: Int? = null
    var startAt: Date? = null
    var endAt: Date? = null
    var orderStatus: OrderStatus? = null
    var paymentStatus: PaymentStatus? = null
    var orderedAt: Date? = null
}
