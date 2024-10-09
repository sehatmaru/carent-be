package xcode.biz.domain.request.auth

import xcode.biz.enums.UserRole
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class LoginRequest {
    val username: String = ""
    val password: String = ""
    val role: UserRole = UserRole.CUSTOMER

    fun validate() {
        if (username.isEmpty() || password.isEmpty()) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
