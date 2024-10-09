package xcode.tenant.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.dto.UserToken
import xcode.biz.domain.repository.TokenRepository
import xcode.biz.domain.repository.UserRepository
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode

@Component
@EnableAsync
class UserInterceptor @Autowired constructor(
    private val tokenRepository: TokenRepository,
    private val userRepository: UserRepository,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var token = request.getHeader("Authorization") ?: request.getHeader("authorization")

        if (token != null) {
            token = token.substring(7)
            val tokenModel = tokenRepository.findByCode(token)

            if (tokenModel != null && !tokenModel.isValid()) {
                throw AppException(ResponseCode.TOKEN_ERROR)
            }

            val userModel = userRepository.getActiveTenantUser(tokenModel!!.userId)
            val userToken = UserToken()
            userModel?.let { BeanUtils.copyProperties(it, userToken) }
            userToken.token = token

            CurrentUser.set(userToken)
        }

        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        CurrentUser.remove()
    }
}
