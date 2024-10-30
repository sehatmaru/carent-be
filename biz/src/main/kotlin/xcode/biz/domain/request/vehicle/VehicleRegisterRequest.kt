package xcode.biz.domain.request.vehicle

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class VehicleRegisterRequest {
    var name = ""
    var transmission: Transmission? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
    var year = ""
    var licenseNumber = ""

    fun validate() {
        if (name.isEmpty() || year.isEmpty() ||
            licenseNumber.isEmpty() || transmission == null
        ) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
