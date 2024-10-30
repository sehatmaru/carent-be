package xcode.biz.domain.request.bill

import xcode.biz.enums.OrderStatus
import xcode.biz.enums.PaymentStatus
import xcode.biz.enums.PaymentType
import java.util.Date

class BillFilterRequest {
    var id: Int? = null
    var customerId: Int? = null
    var invoiceNumber: String? = null
    var orderId: Int? = null
    var paymentType: PaymentType? = null
    var orderStatus: OrderStatus? = null
    var paymentStatus: PaymentStatus? = null
    var createdDate: Date? = null
    var verifiedDate: Date? = null
}
