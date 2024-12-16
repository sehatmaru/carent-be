package xcode.biz.service.cust

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.mapper.OrderMapper
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductReviewListResponse

@Service
class ReviewService @Autowired constructor(
    private val orderMapper: OrderMapper
) {
    fun getProductReviewList(productId: Int): BaseResponse<List<ProductReviewListResponse>> {
        val result = BaseResponse<List<ProductReviewListResponse>>()

        val response = mutableListOf<ProductReviewListResponse>()
        val a = ProductReviewListResponse()
        a.name = "Sara"
        a.rating = 5F
        a.id = 1
        a.review = "Sangat puas"

        val b = ProductReviewListResponse()
        b.name = "Maxwell"
        b.rating = 2.5F
        b.id = 2
        b.review = "Not recomendded"

        response.add(a)
        response.add(b)

        result.setSuccess(orderMapper.getProductReviewList(productId))
        result.setSuccess(response)

        return result
    }
}
