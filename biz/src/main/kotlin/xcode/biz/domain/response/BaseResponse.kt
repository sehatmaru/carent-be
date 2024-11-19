package xcode.biz.domain.response

import org.springframework.http.HttpStatus
import xcode.biz.shared.ResponseCode

class BaseResponse<T> {
    var statusCode = 0
    var message: String? = null
    var result: T? = null

    init {
        setSuccess(null)
    }

    fun setSuccess(data: T?) {
        this.statusCode = HttpStatus.OK.value()
        this.message = ResponseCode.SUCCESS
        this.result = data
    }

    fun setNotFound() {
        this.statusCode = HttpStatus.NOT_FOUND.value()
        this.message = ResponseCode.NOT_FOUND
    }

    fun setUnauthorized(message: String) {
        this.statusCode = HttpStatus.UNAUTHORIZED.value()
        this.message = message
    }

    fun setBadRequest(message: String) {
        this.statusCode = HttpStatus.BAD_REQUEST.value()
        this.message = ResponseCode.FAILED + ": " + message
    }

    fun setConflict(message: String) {
        this.statusCode = HttpStatus.CONFLICT.value()
        this.message = message
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
