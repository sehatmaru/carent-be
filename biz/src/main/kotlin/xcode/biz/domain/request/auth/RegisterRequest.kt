package xcode.biz.domain.request.auth

import xcode.biz.enums.UserRole

class RegisterRequest {
    var fullName = ""
    var username = ""
    var email = ""
    var password = ""
    var mobile = ""
    var company: CompanyRegisterRequest? = null
    var role: UserRole? = null
}
