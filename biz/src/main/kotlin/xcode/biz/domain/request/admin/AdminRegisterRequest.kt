package xcode.biz.domain.request.admin

import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.PARAMS_ERROR

class AdminRegisterRequest {
    var fullName = ""
    var username = ""
    var email = ""
    var password = ""
    var mobile = ""

    fun validate() {
        if (username.isEmpty() || password.isEmpty() ||
            fullName.isEmpty() || email.isEmpty() || mobile.isEmpty()
        ) {
            throw AppException(PARAMS_ERROR)
        }
    }
}
