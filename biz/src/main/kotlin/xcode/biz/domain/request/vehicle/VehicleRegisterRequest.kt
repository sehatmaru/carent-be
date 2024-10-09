package xcode.biz.domain.request.vehicle

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class VehicleRegisterRequest {
    var name = ""
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var engine: EngineType? = null
    var brand: VehicleBrand? = null
    var year = ""
    var licenseNumber = ""

    fun validate() {
        if (name.isEmpty() || year.isEmpty() ||
            licenseNumber.isEmpty() || vehicleType == null ||
            transmission == null
        ) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
