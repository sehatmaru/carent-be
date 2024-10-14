package xcode.cust.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductResponse
import xcode.biz.service.cust.CustomerProductService

@RestController
@RequestMapping(value = ["product"])
class ProductApi @Autowired constructor(
    private val customerProductService: CustomerProductService,
) {

    @PostMapping("/search")
    fun search(@RequestBody @Validated request: ProductSearchRequest): BaseResponse<List<ProductResponse>> {
        return customerProductService.searchProduct(request)
    }
}
