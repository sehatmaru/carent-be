package xcode.biz.domain.request.vehicle

import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class VehicleRegisterRequest {
    var id = 0
    var productId = 0
    var year = ""
    var licenseNumber = ""

    fun validate() {
        if (year.isEmpty() || licenseNumber.isEmpty()) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
