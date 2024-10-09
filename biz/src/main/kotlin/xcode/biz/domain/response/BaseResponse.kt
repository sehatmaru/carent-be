package xcode.biz.domain.response

import org.springframework.http.HttpStatus
import xcode.biz.shared.ResponseCode

class BaseResponse<T> {
    var statusCode = 0
    var message: String? = null
    var result: T? = null

    fun setSuccess(data: T?) {
        this.statusCode = HttpStatus.OK.value()
        this.message = ResponseCode.SUCCESS
        this.result = data
    }

    fun setNotFound() {
        this.statusCode = HttpStatus.NOT_FOUND.value()
        this.message = ResponseCode.NOT_FOUND
    }

    fun setFailed(message: String) {
        this.statusCode = HttpStatus.BAD_REQUEST.value()
        this.message = ResponseCode.FAILED + ": " + message
    }

    fun setInvalidToken(message: String) {
        this.statusCode = HttpStatus.UNAUTHORIZED.value()
        this.message = message
    }

    fun setWrongAuth() {
        this.statusCode = HttpStatus.UNAUTHORIZED.value()
        this.message = ResponseCode.AUTH_ERROR
    }

    fun setWrongParams() {
        this.statusCode = HttpStatus.BAD_REQUEST.value()
        this.message = ResponseCode.PARAMS_ERROR
    }

    fun setInvalidPassword() {
        this.statusCode = HttpStatus.UNAUTHORIZED.value()
        this.message = ResponseCode.INVALID_PASSWORD
    }

    fun setExistData() {
        this.statusCode = HttpStatus.CONFLICT.value()
        this.message = ResponseCode.EXIST
    }

    fun setUsernameExistData() {
        this.statusCode = HttpStatus.CONFLICT.value()
        this.message = ResponseCode.USERNAME_EXIST
    }

    fun setInvalidMethod(message: String?) {
        this.statusCode = HttpStatus.METHOD_NOT_ALLOWED.value()
        this.message = message
    }

    fun setServerError(message: String?) {
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value()
        this.message = message
    }
}
