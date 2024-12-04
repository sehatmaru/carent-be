package xcode.biz.domain.request.product

import java.util.Date
import xcode.biz.enums.EngineType
import xcode.biz.enums.ProductStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class ProductSearchRequest {
    var id: Int? = null
    var name: String? = null
    var priceStart: Int? = null
    var priceEnd: Int? = null
    var provinceId: Int? = null
    var regencyId: Int? = null
    var districtId: Int? = null
    var startDate: Date? = null
    var endDate: Date? = null
    var deliverable: List<Boolean>? = null
    var transmission: List<Transmission>? = null
    var engineType: List<EngineType>? = null
    var brand: List<VehicleBrand>? = null
    var status: ProductStatus? = null
    var duration: Int? = null
    var time: Date? = null
    var capacity: List<Int>? = null

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

        if (startDate == null || endDate == null) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
