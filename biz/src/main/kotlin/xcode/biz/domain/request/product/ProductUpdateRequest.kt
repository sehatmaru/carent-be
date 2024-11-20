package xcode.biz.domain.request.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class ProductUpdateRequest {
    var id = 0
    var name = ""
    var price: Int? = null
    var provinceId: Int? = null
    var regencyId: Int? = null
    var districtId: Int? = null
    var provinceName = ""
    var regencyName = ""
    var districtName = ""
    var deliverable = false
    var transmission: Transmission? = null
    var seat: Int? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
}
