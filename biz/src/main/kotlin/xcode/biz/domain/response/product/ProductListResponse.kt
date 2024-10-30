package xcode.biz.domain.response.product

import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class ProductListResponse {
    var id: Int? = null
    var transmission: Transmission? = null
    var seat: Int? = 0
    var name = ""
    var price: Int? = null
    var deliverable = false
    var brand: VehicleBrand? = null
}
