package xcode.tenant.api

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.product.ProductRegisterRequest
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.request.product.ProductUpdateRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.product.TenantProductListResponse
import xcode.biz.service.tenant.TenantProductService

@RestController
@RequestMapping(value = ["product"])
class ProductApi @Autowired constructor(
    private val tenantProductService: TenantProductService,
) {

    @PostMapping("/register")
    fun register(@RequestBody @Validated request: ProductRegisterRequest): BaseResponse<Int> {
        return tenantProductService.registerProduct(request)
    }

    @PostMapping("/update/{productId}")
    fun register(
        @PathVariable("productId") productId: Int,
        @RequestBody @Validated request: ProductUpdateRequest
    ): BaseResponse<Int> {
        return tenantProductService.updateProduct(productId, request)
    }

    @PostMapping("/delete/{productId}")
    fun register(@PathVariable("productId") productId: Int): BaseResponse<Int> {
        return tenantProductService.deleteProduct(productId)
    }

    @PostMapping("/list")
    fun getProductList(
        @RequestBody @Validated request: ProductSearchRequest,
        @RequestParam("pageNum") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int
    ): BaseResponse<PageInfo<TenantProductListResponse>> {
        return tenantProductService.getProductList(request, pageNumber, pageSize)
    }
}
