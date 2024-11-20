package xcode.biz.domain.response.vehicle

import java.util.Date
import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class VehicleResponse {
    var id: Int? = null
    var productId: Int? = null
    var productName: String? = null
    var engineType: EngineType? = null
    var transmission: Transmission? = null
    var brand: VehicleBrand? = null
    var year = ""
    var status = ""
    var licenseNumber = ""
    var createdDate: Date? = null
}
