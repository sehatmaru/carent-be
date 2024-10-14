package xcode.biz.domain.response.product

import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType

class ProductListResponse {
    var id: Int? = null
    var vehicleName = ""
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var seat: Int? = 0
    var name = ""
    var price: Int? = null
    var deliverable = false
    var brand: VehicleBrand? = null
}
