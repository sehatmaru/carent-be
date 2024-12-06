package xcode.biz.service.cust

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductDetailResponse
import xcode.biz.domain.response.product.ProductFilterCountListResponse
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.domain.response.product.ProductSearchListResponse
import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

@Service
class CustomerProductService @Autowired constructor(
    private val productMapper: ProductMapper,
) {

    fun searchProduct(
        request: ProductSearchRequest,
        limit: Int
    ): BaseResponse<ProductSearchListResponse> {
        val result = BaseResponse<ProductSearchListResponse>()

        val productSearch = ProductSearchListResponse()
        productSearch.list = productMapper.searchProductList(request, limit)
        productSearch.total = productMapper.searchProductListTotal(request)

        result.setSuccess(productSearch)

        return result
    }

    fun getPopularProductList(): BaseResponse<List<ProductListResponse>> {
        val result = BaseResponse<List<ProductListResponse>>()

        result.setSuccess(productMapper.getPopularProductList())

        return result
    }

    fun getRecommendationProductList(limit: Int): BaseResponse<List<ProductListResponse>> {
        val result = BaseResponse<List<ProductListResponse>>()

        result.setSuccess(productMapper.getRecommendationProductList(limit))

        return result
    }

    fun getTotalProduct(): BaseResponse<Int> {
        val result = BaseResponse<Int>()

        result.setSuccess(productMapper.getTotalProduct())

        return result
    }

    fun getProductDetail(id: Int): BaseResponse<ProductDetailResponse> {
        val result = BaseResponse<ProductDetailResponse>()

        result.setSuccess(productMapper.getProductDetail(id))

        return result
    }

    fun getProductFilterCount(
        request: ProductSearchRequest,
    ): BaseResponse<ProductFilterCountListResponse> {
        val result = BaseResponse<ProductFilterCountListResponse>()
        val productFilter = ProductFilterCountListResponse()

        productFilter.toyota = productMapper.getTotalProductBrand(request, VehicleBrand.TOYOTA)
        productFilter.honda = productMapper.getTotalProductBrand(request, VehicleBrand.HONDA)
        productFilter.hyundai = productMapper.getTotalProductBrand(request, VehicleBrand.HYUNDAI)
        productFilter.mazda = productMapper.getTotalProductBrand(request, VehicleBrand.MAZDA)
        productFilter.suzuki = productMapper.getTotalProductBrand(request, VehicleBrand.SUZUKI)
        productFilter.mitsubishi = productMapper.getTotalProductBrand(request, VehicleBrand.MITSUBISHI)
        productFilter.daihatsu = productMapper.getTotalProductBrand(request, VehicleBrand.DAIHATSU)
        productFilter.nissan = productMapper.getTotalProductBrand(request, VehicleBrand.NISSAN)
        productFilter.byd = productMapper.getTotalProductBrand(request, VehicleBrand.BYD)
        productFilter.otherBrand = productMapper.getTotalProductBrand(request, VehicleBrand.OTHER)

        productFilter.selfPickup = productMapper.getTotalProductDeliverable(request, false)
        productFilter.delivery = productMapper.getTotalProductDeliverable(request, true)

        productFilter.gasoline = productMapper.getTotalProductEngine(request, EngineType.GASOLINE)
        productFilter.hybrid = productMapper.getTotalProductEngine(request, EngineType.HYBRID)
        productFilter.electric = productMapper.getTotalProductEngine(request, EngineType.ELECTRIC)
        productFilter.diesel = productMapper.getTotalProductEngine(request, EngineType.DIESEL)

        productFilter.matic = productMapper.getTotalProductTransmission(request, Transmission.MATIC)
        productFilter.manual = productMapper.getTotalProductTransmission(request, Transmission.MANUAL)

        productFilter.sevenSeat = productMapper.getTotalProductSeat(request, 7)
        productFilter.fiveSeat = productMapper.getTotalProductSeat(request, 5)

        result.setSuccess(productFilter)

        return result
    }
}
