package xcode.biz.domain.request.auth

import xcode.biz.enums.UserRole
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class RegisterRequest {
    var fullName = ""
    var username = ""
    var email = ""
    var password = ""
    var mobile = ""
    var company: CompanyRegisterRequest? = null
    var role: UserRole? = null
    val credential: CredentialRegisterRequest? = null

    fun validate() {
        if (username.isEmpty() || password.isEmpty() ||
            fullName.isEmpty() || email.isEmpty() || mobile.isEmpty() || company == null ||
            role == null || credential == null
        ) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
