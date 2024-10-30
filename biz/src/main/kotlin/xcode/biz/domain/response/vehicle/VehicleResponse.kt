package xcode.biz.domain.response.vehicle

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import java.util.Date

class VehicleResponse {
    var id: Int? = null
    var productId: Int? = null
    var productName: String? = null
    var name = ""
    var engineType: EngineType? = null
    var transmission: Transmission? = null
    var brand: VehicleBrand? = null
    var year = ""
    var licenseNumber = ""
    var createdDate: Date? = null
}
