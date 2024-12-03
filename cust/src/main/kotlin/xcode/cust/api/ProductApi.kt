package xcode.cust.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.domain.response.product.ProductSearchListResponse
import xcode.biz.service.cust.CustomerProductService

@RestController
@RequestMapping(value = ["product"])
class ProductApi @Autowired constructor(
    private val customerProductService: CustomerProductService,
) {

    @PostMapping("/search")
    fun search(
        @RequestBody @Validated request: ProductSearchRequest,
        @RequestParam("limit") limit: Int
    ): BaseResponse<ProductSearchListResponse> {
        return customerProductService.searchProduct(request, limit)
    }

    @GetMapping("/popular/list")
    fun getPopularList(
    ): BaseResponse<List<ProductListResponse>> {
        return customerProductService.getPopularProductList()
    }

    @GetMapping("/recommendation/list")
    fun getRecommendationList(
        @RequestParam("limit") limit: Int
    ): BaseResponse<List<ProductListResponse>> {
        return customerProductService.getRecommendationProductList(limit)
    }

    @GetMapping("/total")
    fun getTotalProduct(): BaseResponse<Int> {
        return customerProductService.getTotalProduct()
    }
}
