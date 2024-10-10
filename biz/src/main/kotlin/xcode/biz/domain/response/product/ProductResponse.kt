package xcode.biz.domain.response.product

import java.util.Date
import xcode.biz.domain.response.vehicle.VehicleResponse
import xcode.biz.enums.ProductStatus

class ProductResponse {
    var id: Int? = null
    var vehicle = VehicleResponse()
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
