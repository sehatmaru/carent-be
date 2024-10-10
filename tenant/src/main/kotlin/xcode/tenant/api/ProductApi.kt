package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.product.ProductRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.product.ProductResponse
import xcode.biz.service.tenant.ProductService

@RestController
@RequestMapping(value = ["product"])
class ProductApi @Autowired constructor(
    private val productService: ProductService
) {

    @PostMapping("/register")
    fun register(@RequestBody @Validated request: ProductRegisterRequest): BaseResponse<LoginResponse> {
        return productService.registerProduct(request)
    }

    @GetMapping("/list")
    fun getProductList(): BaseResponse<List<ProductResponse>> {
        return productService.getProductList()
    }
}
