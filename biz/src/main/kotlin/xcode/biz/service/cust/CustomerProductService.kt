package xcode.biz.service.cust

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
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

    fun searchProduct(
        request: ProductSearchRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<ProductListResponse>> {
        val result = BaseResponse<PageInfo<ProductListResponse>>()
        request.validate()

        PageHelper.startPage<ProductListResponse>(pageNum, pageSize)
        result.setSuccess(PageInfo(productMapper.searchProductList(request)))

        return result
    }

    fun getPopularProductList():BaseResponse<List<ProductListResponse>> {
        val result = BaseResponse<List<ProductListResponse>>()

        result.setSuccess(productMapper.getPopularProductList())

        return result
    }

    fun getRecommendationProductList():BaseResponse<List<ProductListResponse>> {
        val result = BaseResponse<List<ProductListResponse>>()

        result.setSuccess(productMapper.getPopularProductList())

        return result
    }
}
