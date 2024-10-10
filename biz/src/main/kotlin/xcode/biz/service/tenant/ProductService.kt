package xcode.biz.service.tenant

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.model.Product
import xcode.biz.domain.repository.ProductRepository
import xcode.biz.domain.repository.VehicleRepository
import xcode.biz.domain.request.product.ProductRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.product.ProductResponse
import xcode.biz.enums.ProductStatus
import xcode.biz.exception.AppException
import xcode.biz.service.GeoService
import xcode.biz.shared.ResponseCode.UNAUTHORIZED
import java.util.Date

@Service
class ProductService @Autowired constructor(
    private val productRepository: ProductRepository,
    private val geoService: GeoService,
    private val vehicleRepository: VehicleRepository,
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
        product.createdAt = Date()

        productRepository.save(product)

        return BaseResponse()
    }

    fun getProductList(): BaseResponse<List<ProductResponse>> {
        val result = BaseResponse<List<ProductResponse>>()

        val productList = productRepository.getProductList(CurrentUser.get().companyId!!) ?: emptyList()
        val responseList = productList.map { product ->
            ProductResponse().also { response ->
                BeanUtils.copyProperties(product, response)
                val vehicle = product.vehicleId?.let { vehicleRepository.getVehicle(it) }

                if (vehicle != null) {
                    BeanUtils.copyProperties(vehicle, response.vehicle)
                }
            }
        }

        result.setSuccess(responseList)

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
