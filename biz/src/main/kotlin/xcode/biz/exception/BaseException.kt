package xcode.biz.exception

import org.springframework.beans.ConversionNotSupportedException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.lang.Nullable
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import xcode.biz.domain.response.BaseResponse
import xcode.biz.shared.ResponseCode.AUTH_ERROR
import xcode.biz.shared.ResponseCode.EXIST
import xcode.biz.shared.ResponseCode.INVALID_OTP_TOKEN
import xcode.biz.shared.ResponseCode.INVALID_PASSWORD
import xcode.biz.shared.ResponseCode.NOT_FOUND
import xcode.biz.shared.ResponseCode.TOKEN_ERROR
import xcode.biz.shared.ResponseCode.USERNAME_EXIST

@ControllerAdvice
class BaseException : ResponseEntityExceptionHandler() {
    private val response: BaseResponse<String> = BaseResponse()

    protected fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?,
    ): ResponseEntity<Any> {
        ex.message?.let { response.setBadRequest(it) }

        return ResponseEntity(response, HttpStatus.OK)
    }

    protected fun handleHttpRequestMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?,
    ): ResponseEntity<Any> {
        response.setInvalidMethod(ex.message)

        return ResponseEntity(response, HttpStatus.OK)
    }

    protected fun handleExceptionInternal(
        ex: Exception,
        @Nullable body: Any?,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?,
    ): ResponseEntity<Any> {
        response.setServerError(ex.message)

        return ResponseEntity(response, HttpStatus.OK)
    }

    protected fun handleConversionNotSupported(
        ex: ConversionNotSupportedException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?,
    ): ResponseEntity<Any> {
        println(ex)
        response.setServerError(ex.message)

        return ResponseEntity(response, HttpStatus.OK)
    }

    protected fun handleMissingPathVariable(
        ex: MissingPathVariableException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?,
    ): ResponseEntity<Any> {
        println(ex)
        response.setBadRequest(ex.message)

        return ResponseEntity(response, HttpStatus.OK)
    }

    protected fun handleHttpMessageNotWritable(
        ex: HttpMessageNotWritableException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?,
    ): ResponseEntity<Any> {
        println(ex)
        ex.message?.let { response.setBadRequest(it) }

        return ResponseEntity(response, HttpStatus.OK)
    }

    @ExceptionHandler(AppException::class)
    fun exception(ex: AppException): ResponseEntity<BaseResponse<String>> {
        when (ex.message) {
            TOKEN_ERROR,
            AUTH_ERROR,
            INVALID_OTP_TOKEN,
            INVALID_PASSWORD,
            -> {
                ex.message?.let { response.setUnauthorized(it) }
            }

            NOT_FOUND -> {
                response.setNotFound()
            }

            EXIST,
            USERNAME_EXIST,
            -> {
                response.setConflict()
            }

            else -> ex.message?.let { response.setBadRequest(it) }
        }
        return ResponseEntity<BaseResponse<String>>(response, HttpStatus.OK)
    }
}
