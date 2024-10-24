package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.admin.AdminRegisterRequest
import xcode.biz.domain.request.customer.CustomerFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.admin.AdminResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.domain.response.customer.TenantCustomerResponse
import xcode.biz.service.tenant.ManagerService

@RestController
@RequestMapping(value = ["manager"])
class ManagerApi @Autowired constructor(
    private val managerService: ManagerService,
) {

    @PostMapping("/admin/register")
    fun login(@RequestBody @Validated request: AdminRegisterRequest): BaseResponse<LoginResponse> {
        return managerService.registerAdmin(request)
    }

    @PostMapping("/admin/deactivate/{userId}")
    fun register(@PathVariable("userId") @Validated userId: Int): BaseResponse<RegisterResponse> {
        return managerService.deactivateAdmin(userId)
    }

    @GetMapping("/admin/list")
    fun getAdminList(): BaseResponse<List<AdminResponse>> {
        return managerService.getAdminList()
    }

    @PostMapping("/customer/list")
    fun getCustomerList(@RequestBody request: CustomerFilterRequest): BaseResponse<List<TenantCustomerResponse>> {
        return managerService.getTenantCustomerList(request)
    }
}
