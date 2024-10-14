package xcode.biz.domain.response.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.ProductStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleType
import java.util.Date

class ProductResponse {
    var id: Int? = null
    var vehicleName = ""
    var vehicleType: VehicleType? = null
    var engineType: EngineType? = null
    var transmission: Transmission? = null
    var year = ""
    var licenseNumber = ""
    var name = ""
    var price: Int? = null
    var provinceId: Int? = null
    var provinceName = ""
    var regencyId: Int? = null
    var regencyName = ""
    var districtId: Int? = null
    var districtName = ""
    var deliverable = false
    var status: ProductStatus? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var deletedAt: Date? = null
}
