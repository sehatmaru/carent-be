package xcode.biz.service.cust

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.domain.response.product.ProductSearchListResponse

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
}
