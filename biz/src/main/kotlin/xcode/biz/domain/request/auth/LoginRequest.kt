package xcode.biz.domain.request.auth

import xcode.biz.enums.UserRole

class LoginRequest {
    val username: String = ""
    val password: String = ""
    val role: UserRole = UserRole.CUSTOMER
}
