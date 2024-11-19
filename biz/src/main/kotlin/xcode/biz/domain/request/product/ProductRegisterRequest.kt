package xcode.biz.domain.request.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class ProductRegisterRequest {
    var id = 0
    var name = ""
    var vehicleId: Int? = null
    var price: Int? = null
    var provinceId: Int? = null
    var regencyId: Int? = null
    var districtId: Int? = null
    var deliverable = false
    var companyId: Int? = null
    var provinceName = ""
    var regencyName = ""
    var districtName = ""
    var transmission: Transmission? = null
    var seat: Int? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null

    fun validate() {
        if (name.isEmpty() || price == null
        ) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
