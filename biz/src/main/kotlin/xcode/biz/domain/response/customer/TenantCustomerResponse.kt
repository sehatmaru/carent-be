package xcode.biz.domain.response.customer

import java.util.Date

class TenantCustomerResponse {
    var name = ""
    var username = ""
    var mobile = ""
    var lastOrder: Date? = null
    var totalOrder = 0
}
