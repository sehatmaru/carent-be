package xcode.biz.domain.response.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType

class ProductDetailResponse {
    var id: Int? = null
    var vehicleName = ""
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var provinceName = ""
    var regencyName = ""
    var districtName = ""
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
    var companyName = ""
    var seat = ""
    var name = ""
    var price: Int? = null
    var deliverable = false
}
