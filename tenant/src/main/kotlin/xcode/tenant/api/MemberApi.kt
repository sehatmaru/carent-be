package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.member.AdminRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.service.tenant.MemberService

@RestController
@RequestMapping(value = ["member"])
class MemberApi @Autowired constructor(
    private val memberService: MemberService,
) {

    @PostMapping("/admin/register")
    fun login(@RequestBody @Validated request: AdminRegisterRequest): BaseResponse<LoginResponse> {
        return memberService.registerAdmin(request)
    }

    @PostMapping("/admin/deactivate/{userId}")
    fun register(@PathVariable("userId") @Validated userId: Int): BaseResponse<RegisterResponse> {
        return memberService.deactivateAdmin(userId)
    }
}
