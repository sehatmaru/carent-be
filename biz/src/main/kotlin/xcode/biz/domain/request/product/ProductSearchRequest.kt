package xcode.biz.domain.request.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.ProductStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR
import java.util.Date

class ProductSearchRequest {
    var id: Int? = null
    var name: String? = null
    var priceStart: Int? = null
    var priceEnd: Int? = null
    var provinceId: Int? = null
    var regencyId: Int? = null
    var districtId: Int? = null
    var startAt: Date? = null
    var endAt: Date? = null
    var deliverable: Boolean? = null
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
    var status: ProductStatus? = null

    fun validate() {
        if (priceStart != null) {
            if (priceEnd == null) {
                throw AppException(PARAMS_ERROR)
            }
        }

        if (priceEnd != null) {
            if (priceStart == null) {
                throw AppException(PARAMS_ERROR)
            }
        }

        if (startAt == null || endAt == null) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
