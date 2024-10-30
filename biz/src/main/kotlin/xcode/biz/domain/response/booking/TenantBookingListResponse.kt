package xcode.biz.domain.response.booking

import xcode.biz.enums.PickupType
import java.util.Date

class TenantBookingListResponse {
    var id: Int? = null
    var customerId: Int? = null
    var customerName = ""
    var orderId: Int? = null
    var billIid: Int? = null
    var invoiceNumber = ""
    var productId: Int? = null
    var productName = ""
    var pickupType: PickupType? = null
    var startDate: Date? = null
    var endDate: Date? = null
}
