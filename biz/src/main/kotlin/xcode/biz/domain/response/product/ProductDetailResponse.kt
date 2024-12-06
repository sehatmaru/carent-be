package xcode.biz.domain.response.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class ProductDetailResponse {
    var id: Int? = null
    var transmission: Transmission? = null
    var seat: Int? = 0
    var name = ""
    var price: Int? = null
    var rating: Float? = null
    var deliverable = false
    var brand: VehicleBrand? = null
    var provinceName = ""
    var regencyName = ""
    var districtName = ""
    var engineType: EngineType? = null
    var companyName = ""
}
