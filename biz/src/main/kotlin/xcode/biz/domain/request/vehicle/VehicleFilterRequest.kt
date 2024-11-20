package xcode.biz.domain.request.vehicle

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class VehicleFilterRequest {
    var id: Int? = null
    var productId: Int? = null
    var engineType: EngineType? = null
    var transmission: Transmission? = null
    var brand: VehicleBrand? = null
    var year: String? = null
    var licenseNumber: String? = null
}
