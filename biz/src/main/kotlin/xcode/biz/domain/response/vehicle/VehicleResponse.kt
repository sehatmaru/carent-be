package xcode.biz.domain.response.vehicle

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import java.util.Date

class VehicleResponse {
    var id: Int? = null
    var name = ""
    var engineType: EngineType? = null
    var transmission: Transmission? = null
    var year = ""
    var licenseNumber = ""
    var createdBy: Int? = null
    var createdDate: Date? = null
    var updatedDate: Date? = null
    var deletedDate: Date? = null
}
