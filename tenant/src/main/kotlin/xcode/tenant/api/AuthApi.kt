package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.auth.LoginRequest
import xcode.biz.domain.request.auth.RegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.service.AuthService

@RestController
@RequestMapping(value = ["auth"])
class AuthApi @Autowired constructor(
    private val authService: AuthService,
) {

    @PostMapping("/login")
    fun login(@RequestBody @Validated request: LoginRequest): BaseResponse<LoginResponse> {
        return authService.login(request)
    }

    @PostMapping("/register")
    fun register(@RequestBody @Validated request: RegisterRequest): BaseResponse<RegisterResponse> {
        return authService.register(request)
    }

    @PostMapping("/otp/verify/{otpCode}")
    fun verifyOtp(@PathVariable("otpCode") @Validated otpCode: String): BaseResponse<RegisterResponse> {
        return authService.verifyOtp(otpCode)
    }

    @PostMapping("/logout")
    fun logout(): BaseResponse<RegisterResponse> {
        return authService.logout()
    }
}
