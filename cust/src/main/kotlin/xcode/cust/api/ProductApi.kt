package xcode.cust.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductDetailResponse
import xcode.biz.domain.response.product.ProductFilterCountListResponse
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.domain.response.product.ProductReviewListResponse
import xcode.biz.domain.response.product.ProductSearchListResponse
import xcode.biz.service.cust.CustomerProductService
import xcode.biz.service.cust.ReviewService

@RestController
@RequestMapping(value = ["product"])
class ProductApi @Autowired constructor(
    private val customerProductService: CustomerProductService,
    private val reviewService: ReviewService
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
        @RequestParam("limit") limit: Int
    ): BaseResponse<List<ProductListResponse>> {
        return customerProductService.getPopularProductList(limit)
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

    @PostMapping("/filter/count")
    fun getProductFilterCount(
        @RequestBody @Validated request: ProductSearchRequest
    ): BaseResponse<ProductFilterCountListResponse> {
        return customerProductService.getProductFilterCount(request)
    }

    @GetMapping("/detail/{productId}")
    fun getProductDetail(
        @PathVariable("productId") @Validated productId: Int
    ): BaseResponse<ProductDetailResponse> {
        return customerProductService.getProductDetail(productId)
    }

    @GetMapping("/detail/reviews/{productId}")
    fun getProductReviews(
        @PathVariable("productId") @Validated productId: Int
    ): BaseResponse<List<ProductReviewListResponse>> {
        return reviewService.getProductReviewList(productId)
    }
}
