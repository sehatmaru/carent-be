package xcode.biz.domain.request.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class ProductUpdateRequest {
    var id = 0
    var name = ""
    var price: Int? = null
    var deliverable = false
    var quantity: Int? = null
    var available: Int? = null
    var transmission: Transmission? = null
    var seat: Int? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
}
