package xcode.biz.domain.response.vehicle

import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleType
import java.util.Date

class VehicleResponse {
    var id: Int? = null
    var name = ""
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var year = ""
    var licenseNumber = ""
    var createdBy: Int? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var deletedAt: Date? = null
}
