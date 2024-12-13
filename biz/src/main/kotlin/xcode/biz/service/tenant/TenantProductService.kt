package xcode.biz.service.tenant

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.request.product.ProductRegisterRequest
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.request.product.ProductUpdateRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductOptionListResponse
import xcode.biz.domain.response.product.TenantProductListResponse
import xcode.biz.exception.AppException
import xcode.biz.service.GeoService
import xcode.biz.shared.ResponseCode.UNAUTHORIZED

@Service
class TenantProductService @Autowired constructor(
    private val productMapper: ProductMapper,
    private val geoService: GeoService
) {

    fun registerProduct(request: ProductRegisterRequest): BaseResponse<Int> {
        checkPermission()
        request.validate()

        request.provinceName =
            request.provinceId?.let { geoService.getProvince(it).result?.name ?: "NOT FOUND" }.toString()
        request.regencyName =
            request.regencyId?.let { geoService.getRegency(it).result?.name ?: "NOT FOUND" }.toString()
        request.districtName =
            request.districtId?.let { geoService.getDistrict(it).result?.name ?: "NOT FOUND" }.toString()
        request.companyId = CurrentUser.get().companyId

        productMapper.insertProduct(request)

        return BaseResponse()
    }

    fun updateProduct(productId: Int, request: ProductUpdateRequest): BaseResponse<Int> {
        checkPermission()

        request.provinceName =
            request.provinceId?.let { geoService.getProvince(it).result?.name ?: "NOT FOUND" }.toString()
        request.regencyName =
            request.regencyId?.let { geoService.getRegency(it).result?.name ?: "NOT FOUND" }.toString()
        request.districtName =
            request.districtId?.let { geoService.getDistrict(it).result?.name ?: "NOT FOUND" }.toString()

        productMapper.updateProduct(productId, request)

        return BaseResponse()
    }

    fun deleteProduct(productId: Int): BaseResponse<Int> {
        checkPermission()

        productMapper.deleteProduct(productId)

        return BaseResponse()
    }

    fun getProductList(
        request: ProductSearchRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<TenantProductListResponse>> {
        val result = BaseResponse<PageInfo<TenantProductListResponse>>()

        PageHelper.startPage<TenantProductListResponse>(pageNum, pageSize)
        result.setSuccess(PageInfo(productMapper.getTenantProductList(CurrentUser.get().companyId!!, request)))

        return result
    }

    fun getProductOptionList(name: String): BaseResponse<List<ProductOptionListResponse>> {
        val result = BaseResponse<List<ProductOptionListResponse>>()

        result.setSuccess(productMapper.getTenantProductOptionList(CurrentUser.get().companyId!!, name))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
