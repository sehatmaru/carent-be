package xcode.biz.domain.response.admin

import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleStatus
import xcode.biz.enums.VehicleType
import java.util.Date

class VehicleResponse {
    var id: Int? = null
    var name = ""
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var year = ""
    var licenseNumber = ""
    var status: VehicleStatus? = null
    var createdBy: Int? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var deletedAt: Date? = null
}
