package xcode.biz.domain.response.auth

import xcode.biz.enums.UserRole

class LoginResponse {
    var accessToken = ""
    var username = ""
    var role: UserRole? = null
}
