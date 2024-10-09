package xcode.biz.service.tenant

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.model.CurrentAuth
import xcode.biz.domain.model.User
import xcode.biz.domain.repository.UserRepository
import xcode.biz.domain.request.admin.AdminRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.admin.AdminResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.enums.UserRole
import xcode.biz.exception.AppException
import xcode.biz.service.JasyptService
import xcode.biz.shared.ResponseCode.NOT_FOUND
import xcode.biz.shared.ResponseCode.UNAUTHORIZED
import xcode.biz.shared.ResponseCode.USERNAME_EXIST
import java.util.Date

@Service
class ManagerService @Autowired constructor(
    private val userRepository: UserRepository,
    private val jasyptService: JasyptService,
) {

    fun registerAdmin(request: AdminRegisterRequest): BaseResponse<LoginResponse> {
        checkPermission()
        request.validate()

        if (userRepository.getActiveUser(request.username, request.email) != null) {
            throw AppException(USERNAME_EXIST)
        }

        val manager = userRepository.getActiveTenantManager(CurrentAuth.get().userId)

        val user = User()
        user.username = request.username
        user.fullName = request.fullName
        user.email = request.email
        user.mobile = request.mobile
        user.password = jasyptService.encryptor(request.password, true)
        user.createdAt = Date()
        user.role = UserRole.TENANT_ADMIN
        user.createdBy = manager!!.id
        user.verifiedAt = Date()
        user.companyId = manager.companyId

        userRepository.save(user)

        return BaseResponse()
    }

    fun deactivateAdmin(userId: Int): BaseResponse<RegisterResponse> {
        val user = userRepository.getActiveTenantAdmin(userId) ?: throw AppException(NOT_FOUND)

        if (user.createdBy != CurrentAuth.get().userId) {
            throw AppException(UNAUTHORIZED)
        }

        user.deletedAt = Date()

        userRepository.save(user)

        return BaseResponse()
    }

    fun getAdminList(): BaseResponse<List<AdminResponse>> {
        val result = BaseResponse<List<AdminResponse>>()

        checkPermission()

        val userList = userRepository.getAdminList(CurrentAuth.get().userId) ?: emptyList()
        val responseList = userList.map { user ->
            AdminResponse().also { response ->
                BeanUtils.copyProperties(user, response)
            }
        }

        result.setSuccess(responseList)

        return result
    }

    fun checkPermission() {
        if (!CurrentAuth.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
