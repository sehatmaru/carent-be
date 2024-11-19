package xcode.biz.service.tenant

import com.github.pagehelper.PageInfo
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.OrderMapper
import xcode.biz.domain.mapper.UserMapper
import xcode.biz.domain.model.User
import xcode.biz.domain.request.admin.AdminRegisterRequest
import xcode.biz.domain.request.admin.AdminUpdateRequest
import xcode.biz.domain.request.customer.CustomerFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.admin.AdminResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.domain.response.customer.TenantCustomerResponse
import xcode.biz.enums.UserRole
import xcode.biz.exception.AppException
import xcode.biz.service.JasyptService
import xcode.biz.shared.ResponseCode.NOT_FOUND
import xcode.biz.shared.ResponseCode.UNAUTHORIZED
import xcode.biz.shared.ResponseCode.USERNAME_EXIST

@Service
class ManagerService @Autowired constructor(
    private val userMapper: UserMapper,
    private val jasyptService: JasyptService,
    private val orderMapper: OrderMapper,
) {

    fun registerAdmin(request: AdminRegisterRequest): BaseResponse<LoginResponse> {
        checkPermission()
        request.validate()

        userMapper.getActiveUser(request.username, request.email)?.let {
            throw AppException(USERNAME_EXIST)
        }

        val user = User()
        user.username = request.username
        user.fullName = request.fullName
        user.email = request.email
        user.mobile = request.mobile
        user.password = jasyptService.encryptor(request.password, true)
        user.createdDate = Date()
        user.role = UserRole.TENANT_ADMIN
        user.createdBy = CurrentUser.get().id
        user.verifiedDate = Date()
        user.companyId = CurrentUser.get().companyId

        userMapper.insertUser(user)

        return BaseResponse()
    }

    fun deactivateAdmin(userId: Int): BaseResponse<RegisterResponse> {
        val user = userMapper.getActiveTenantAdmin(userId) ?: throw AppException(NOT_FOUND)

        if (user.createdBy != CurrentUser.get().id) {
            throw AppException(UNAUTHORIZED)
        }

        user.deletedDate = Date()

        userMapper.insertUser(user)

        return BaseResponse()
    }

    fun updateAdmin(adminId: Int, request: AdminUpdateRequest): BaseResponse<Int> {
        val user = userMapper.getActiveTenantAdmin(adminId) ?: throw AppException(NOT_FOUND)

        if (user.createdBy != CurrentUser.get().id) {
            throw AppException(UNAUTHORIZED)
        }

        if (user.email != request.email || user.username != request.username) {
            userMapper.getActiveUser(request.username, request.email)?.let {
                throw AppException(USERNAME_EXIST)
            }
        }

        userMapper.updateAdmin(adminId, request)

        return BaseResponse()
    }

    fun deleteAdmin(adminId: Int): BaseResponse<Int> {
        val user = userMapper.getActiveTenantAdmin(adminId) ?: throw AppException(NOT_FOUND)

        if (user.createdBy != CurrentUser.get().id) {
            throw AppException(UNAUTHORIZED)
        }

        userMapper.deleteAdmin(adminId)

        return BaseResponse()
    }

    fun getAdminList(
        request: CustomerFilterRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<AdminResponse>> {
        val result = BaseResponse<PageInfo<AdminResponse>>()

        checkPermission()

        result.setSuccess(PageInfo(userMapper.getAdminList(CurrentUser.get().companyId!!, request)))

        return result
    }

    fun getTenantCustomerList(
        request: CustomerFilterRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<TenantCustomerResponse>> {
        val result = BaseResponse<PageInfo<TenantCustomerResponse>>()

        result.setSuccess(PageInfo(orderMapper.getTenantCustomerList(CurrentUser.get().companyId!!, request)))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
