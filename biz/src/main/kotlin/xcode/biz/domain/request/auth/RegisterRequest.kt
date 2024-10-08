package xcode.biz.domain.request.auth

class RegisterRequest {
    var fullName = ""
    var username = ""
    var email = ""
    var password = ""
    var mobile = ""
    var company: CompanyRegisterRequest? = null
}
