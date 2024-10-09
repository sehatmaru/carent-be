package xcode.biz.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.model.Company
import xcode.biz.domain.model.Otp
import xcode.biz.domain.model.Token
import xcode.biz.domain.model.User
import xcode.biz.domain.repository.CompanyRepository
import xcode.biz.domain.repository.OtpRepository
import xcode.biz.domain.repository.TokenRepository
import xcode.biz.domain.repository.UserRepository
import xcode.biz.domain.request.auth.LoginRequest
import xcode.biz.domain.request.auth.RegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.auth.RegisterResponse
import xcode.biz.enums.TokenType
import xcode.biz.enums.UserRole
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode
import xcode.biz.shared.ResponseCode.AUTH_ERROR
import xcode.biz.shared.ResponseCode.INVALID_OTP_TOKEN
import xcode.biz.shared.ResponseCode.USERNAME_EXIST
import xcode.biz.utils.CommonUtil.generateOTP
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Service
class AuthService @Autowired constructor(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val tokenRepository: TokenRepository,
    private val otpRepository: OtpRepository,
    private val jasyptService: JasyptService,
    private val emailService: EmailService,
) {

    fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        val baseResponse = BaseResponse<LoginResponse>()

        request.validate()

        val user = if (request.role == UserRole.CUSTOMER) {
            userRepository.getActiveCustomer(request.username)
        } else {
            userRepository.getActiveTenantUserByUsername(request.username)
        }

        if (user == null || request.password != jasyptService.encryptor(user.password, false)) {
            throw AppException(AUTH_ERROR)
        }

        val token = Token()
        token.code = jwtService.generateToken(user)
        token.userId = user.id
        token.type = TokenType.NON_OTP
        token.userRole = user.role
        token.expireAt = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())

        tokenRepository.save(token)

        val response = LoginResponse()
        response.accessToken = token.code

        baseResponse.setSuccess(response)

        return baseResponse
    }

    fun register(request: RegisterRequest): BaseResponse<RegisterResponse> {
        val baseResponse = BaseResponse<RegisterResponse>()

        request.validate()

        if (userRepository.getActiveUser(request.username, request.email) != null) {
            throw AppException(USERNAME_EXIST)
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
        user.role = request.role!!
        user.companyId = company.id
        user.credentialNo = request.credential!!.credentialNo
        user.credentialType = request.credential.credentialType
        user.createdAt = Date()

        userRepository.save(user)

        val otp = Otp()
        otp.code = generateOTP()
        otp.userId = user.id

        emailService.sendOtpEmail(user.email, otp.code)

        otpRepository.save(otp)

        val token = Token()
        token.code = jwtService.generateToken(user)
        token.userId = user.id
        token.type = TokenType.OTP
        token.isActive = true
        token.expireAt = Date.from(LocalDateTime.now().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant())
        token.userRole = user.role

        tokenRepository.save(token)

        val response = RegisterResponse()
        response.otpToken = token.code

        baseResponse.setSuccess(response)

        return baseResponse
    }

    fun verifyOtp(otpCode: String): BaseResponse<RegisterResponse> {
        val otp = otpRepository.getUnverifiedOtp(otpCode)
        val token = tokenRepository.getOtpToken(CurrentUser.get().token)
        val tokens = CurrentUser.get().token

        if (otp == null || token == null || !token.isValid()) {
            throw AppException(INVALID_OTP_TOKEN)
        }

        val user = userRepository.getInactiveUser(token.userId)

        user!!.verifiedAt = Date()
        otp.verifiedAt = Date()
        token.isActive = false

        userRepository.save(user)
        otpRepository.save(otp)
        tokenRepository.save(token)

        return BaseResponse()
    }

    fun logout(): BaseResponse<RegisterResponse> {
        val token = tokenRepository.findByCode(CurrentUser.get().token)

        if (token == null) {
            throw AppException(ResponseCode.NOT_FOUND)
        } else {
            tokenRepository.delete(token)
        }

        return BaseResponse()
    }
}
