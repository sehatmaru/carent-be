package xcode.biz.domain.response.bill

import xcode.biz.enums.OrderStatus
import xcode.biz.enums.PaymentStatus
import xcode.biz.enums.PaymentType
import java.util.Date

class BillListResponse {
    var id: Int? = null
    var customerId: Int? = null
    var customerName = ""
    var orderId: Int? = 0
    var totalPayment: Int? = null
    var totalPaid: Int? = null
    var paymentType: PaymentType? = null
    var orderStatus: OrderStatus? = null
    var paymentStatus: PaymentStatus? = null
    var invoiceNumber = ""
    var createdDate: Date? = null
    var verifiedDate: Date? = null
}
