package xcode.cust.api

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.service.cust.CustomerProductService

@RestController
@RequestMapping(value = ["product"])
class ProductApi @Autowired constructor(
    private val customerProductService: CustomerProductService,
) {

    @PostMapping("/search")
    fun search(
        @RequestBody @Validated request: ProductSearchRequest,
        @RequestParam("pageNum") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int
    ): BaseResponse<PageInfo<ProductListResponse>> {
        return customerProductService.searchProduct(request, pageNumber, pageSize)
    }
}
