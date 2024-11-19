package xcode.tenant.api

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.admin.AdminRegisterRequest
import xcode.biz.domain.request.admin.AdminUpdateRequest
import xcode.biz.domain.request.company.CompanyUpdateRequest
import xcode.biz.domain.request.customer.CustomerFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.admin.AdminResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.domain.response.company.CompanyResponse
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

    @PostMapping("/admin/list")
    fun getAdminList(
        @RequestBody request: CustomerFilterRequest,
        @RequestParam("pageNum") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int
    ): BaseResponse<PageInfo<AdminResponse>> {
        return managerService.getAdminList(request, pageNumber, pageSize)
    }

    @PostMapping("/customer/list")
    fun getCustomerList(
        @RequestBody request: CustomerFilterRequest,
        @RequestParam("pageNum") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int
    ): BaseResponse<PageInfo<TenantCustomerResponse>> {
        return managerService.getTenantCustomerList(request, pageNumber, pageSize)
    }

    @PostMapping("/admin/update/{adminId}")
    fun updateAdmin(
        @PathVariable("adminId") adminId: Int,
        @RequestBody @Validated request: AdminUpdateRequest
    ): BaseResponse<Int> {
        return managerService.updateAdmin(adminId, request)
    }

    @PostMapping("/admin/delete/{adminId}")
    fun deleteAdmin(@PathVariable("adminId") adminId: Int): BaseResponse<Int> {
        return managerService.deleteAdmin(adminId)
    }

    @GetMapping("/company/detail")
    fun getCompanyDetail(): BaseResponse<CompanyResponse> {
        return managerService.getCompanyDetail()
    }

    @PostMapping("/company/update")
    fun updateCompany(
        @RequestBody @Validated request: CompanyUpdateRequest
    ): BaseResponse<Boolean> {
        return managerService.updateCompany(request)
    }
}
