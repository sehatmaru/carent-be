package xcode.admin.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.auth.LoginRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
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
}
