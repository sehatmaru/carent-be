package xcode.biz.domain.response.product

import xcode.biz.enums.EngineType
import xcode.biz.enums.ProductStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

class TenantProductListResponse {
    var id: Int? = null
    var transmission: Transmission? = null
    var seat: Int? = 0
    var name = ""
    var price: Int? = null
    var quantity: Int? = null
    var provinceId: Int? = null
    var provinceName = ""
    var regencyId: Int? = null
    var regencyName = ""
    var districtId: Int? = null
    var districtName = ""
    var available: Int? = null
    var deliverable = false
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
    var status: ProductStatus? = null
}
