package xcode.tenant.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.model.Company
import xcode.biz.domain.model.CurrentAuth
import xcode.biz.domain.model.Token
import xcode.biz.domain.model.User
import xcode.biz.domain.repository.CompanyRepository
import xcode.biz.domain.repository.TokenRepository
import xcode.biz.domain.repository.UserRepository
import xcode.biz.domain.request.auth.LoginRequest
import xcode.biz.domain.request.auth.RegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.enums.UserRole
import xcode.biz.exception.AppException
import xcode.biz.service.EmailService
import xcode.biz.service.JasyptService
import xcode.biz.service.JwtService
import xcode.biz.shared.ResponseCode
import xcode.biz.shared.ResponseCode.AUTH_ERROR_MESSAGE
import xcode.biz.shared.ResponseCode.PARAMS_ERROR_MESSAGE
import xcode.biz.shared.ResponseCode.USERNAME_EXIST_MESSAGE
import xcode.biz.utils.CommonUtil.generateOTP
import xcode.biz.utils.CommonUtil.getTomorrowDate
import java.util.*

@Service
class AuthService @Autowired constructor(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val tokenRepository: TokenRepository,
    private val jasyptService: JasyptService,
    private val emailService: EmailService,
) {

    fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        val baseResponse = BaseResponse<LoginResponse>()

        if (request.username.isEmpty() || request.password.isEmpty()) {
            throw AppException(PARAMS_ERROR_MESSAGE)
        }

        val user = userRepository.getActiveTenantUser(request.username)

        if (user == null || request.password != jasyptService.encryptor(user.password, false)) {
            throw AppException(AUTH_ERROR_MESSAGE)
        }

        val token = jwtService.generateToken(user)
        tokenRepository.save(Token(token, user.id, getTomorrowDate()))

        val response = LoginResponse()
        response.accessToken = token

        baseResponse.setSuccess(response)

        return baseResponse
    }

    fun register(request: RegisterRequest): BaseResponse<RegisterResponse> {
        val baseResponse = BaseResponse<RegisterResponse>()

        if (request.username.isEmpty() || request.password.isEmpty() ||
            request.fullName.isEmpty() || request.email.isEmpty() || request.company == null
        ) {
            throw AppException(PARAMS_ERROR_MESSAGE)
        }

        if (userRepository.getActiveTenantUser(request.username) != null) {
            throw AppException(USERNAME_EXIST_MESSAGE)
        }

        val company = Company()
        BeanUtils.copyProperties(request.company!!, company)

        companyRepository.save(company)

        val user = User()
        user.username = request.username
        user.fullName = request.fullName
        user.email = request.email
        user.mobile = request.mobile
        user.password = jasyptService.encryptor(request.password, true)
        user.role = UserRole.TENANT_MANAGER
        user.companyId = company.id
        user.createdAt = Date()

        userRepository.save(user)

        val response = RegisterResponse()
        response.username = user.username

        emailService.sendOtpEmail(user.email, generateOTP())

        baseResponse.setSuccess(response)

        return baseResponse
    }

    fun logout() {
        val token = tokenRepository.findByToken(CurrentAuth.get().token)

        if (token == null) {
            throw AppException(ResponseCode.NOT_FOUND_MESSAGE)
        } else {
            tokenRepository.delete(token)
        }
    }
}
