package xcode.biz.service.tenant

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.mapper.VehicleMapper
import xcode.biz.domain.model.Product
import xcode.biz.domain.request.product.ProductRegisterRequest
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.product.TenantProductListResponse
import xcode.biz.enums.ProductStatus
import xcode.biz.exception.AppException
import xcode.biz.service.GeoService
import xcode.biz.shared.ResponseCode.UNAUTHORIZED
import java.util.Date

@Service
class TenantProductService @Autowired constructor(
    private val productMapper: ProductMapper,
    private val geoService: GeoService,
    private val vehicleMapper: VehicleMapper,
) {

    fun registerProduct(request: ProductRegisterRequest): BaseResponse<LoginResponse> {
        checkPermission()
        request.validate()

        val product = Product()
        BeanUtils.copyProperties(request, product)
        product.status = ProductStatus.AVAILABLE
        product.provinceName = request.provinceId?.let { geoService.getProvince(it).result?.name ?: "NOT FOUND" }.toString()
        product.regencyName = request.regencyId?.let { geoService.getRegency(it).result?.name ?: "NOT FOUND" }.toString()
        product.districtName = request.districtId?.let { geoService.getDistrict(it).result?.name ?: "NOT FOUND" }.toString()
        product.createdDate = Date()

        productMapper.insertProduct(product)

        return BaseResponse()
    }

    fun getProductList(request: ProductSearchRequest): BaseResponse<List<TenantProductListResponse>> {
        val result = BaseResponse<List<TenantProductListResponse>>()

        result.setSuccess(productMapper.getTenantProductList(CurrentUser.get().companyId!!, request))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
