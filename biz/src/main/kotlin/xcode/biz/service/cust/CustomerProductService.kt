package xcode.biz.service.cust

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductResponse

@Service
class CustomerProductService @Autowired constructor(
    private val productMapper: ProductMapper,
) {

    fun searchProduct(request: ProductSearchRequest): BaseResponse<List<ProductResponse>> {
        val result = BaseResponse<List<ProductResponse>>()
        request.validate()

        val productList = productMapper.searchProductList(request)

        result.setSuccess(productList)

        return result
    }
}
