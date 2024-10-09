package xcode.biz.service.tenant

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
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

        val user = User()
        user.username = request.username
        user.fullName = request.fullName
        user.email = request.email
        user.mobile = request.mobile
        user.password = jasyptService.encryptor(request.password, true)
        user.createdAt = Date()
        user.role = UserRole.TENANT_ADMIN
        user.createdBy = CurrentUser.get().id
        user.verifiedAt = Date()
        user.companyId = CurrentUser.get().companyId

        userRepository.save(user)

        return BaseResponse()
    }

    fun deactivateAdmin(userId: Int): BaseResponse<RegisterResponse> {
        val user = userRepository.getActiveTenantAdmin(userId) ?: throw AppException(NOT_FOUND)

        if (user.createdBy != CurrentUser.get().id) {
            throw AppException(UNAUTHORIZED)
        }

        user.deletedAt = Date()

        userRepository.save(user)

        return BaseResponse()
    }

    fun getAdminList(): BaseResponse<List<AdminResponse>> {
        val result = BaseResponse<List<AdminResponse>>()

        checkPermission()

        val userList = userRepository.getAdminList(CurrentUser.get().id!!) ?: emptyList()
        val responseList = userList.map { user ->
            AdminResponse().also { response ->
                BeanUtils.copyProperties(user, response)
            }
        }

        result.setSuccess(responseList)

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
