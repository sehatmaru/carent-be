package xcode.biz.domain.request.booking

import xcode.biz.enums.PickupType
import java.util.Date

class BookingFilterRequest {
    var id: Int? = null
    var customerId: Int? = null
    var invoiceNumber: Int? = null
    var pickupType: PickupType? = null
    var startDate: Date? = null
    var endDate: Date? = null
}
