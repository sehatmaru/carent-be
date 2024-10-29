package xcode.biz.service.cust

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductListResponse

@Service
class CustomerProductService @Autowired constructor(
    private val productMapper: ProductMapper,
) {

    fun searchProduct(request: ProductSearchRequest): BaseResponse<List<ProductListResponse>> {
        val result = BaseResponse<List<ProductListResponse>>()
        request.validate()

        result.setSuccess(productMapper.searchProductList(request))

        return result
    }

    fun getProductDetail(productId: Int): BaseResponse<ProductListResponse> {
        val result = BaseResponse<ProductListResponse>()

        return result
    }
}
