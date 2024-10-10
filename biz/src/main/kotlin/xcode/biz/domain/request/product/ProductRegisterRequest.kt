package xcode.biz.domain.request.product

import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class ProductRegisterRequest {
    var name = ""
    var vehicleId: Int? = null
    var price: Int? = null
    var provinceId: Int? = null
    var regencyId: Int? = null
    var districtId: Int? = null
    var deliverable = false

    fun validate() {
        if (name.isEmpty() || vehicleId == null || regencyId == null ||
            price == null || provinceId == null || districtId == null
        ) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
