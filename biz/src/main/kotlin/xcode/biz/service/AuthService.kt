package xcode.biz.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.CompanyMapper
import xcode.biz.domain.mapper.OtpMapper
import xcode.biz.domain.mapper.TokenMapper
import xcode.biz.domain.mapper.UserMapper
import xcode.biz.domain.model.Company
import xcode.biz.domain.model.Otp
import xcode.biz.domain.model.Token
import xcode.biz.domain.model.User
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
    private val userMapper: UserMapper,
    private val companyMapper: CompanyMapper,
    private val tokenMapper: TokenMapper,
    private val otpMapper: OtpMapper,
    private val jasyptService: JasyptService,
    private val emailService: EmailService,
) {

    fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        val baseResponse = BaseResponse<LoginResponse>()

        request.validate()

        val user = when (request.role) {
            UserRole.CUSTOMER -> {
                userMapper.getActiveCustomer(request.username)
            }
            UserRole.ADMIN -> {
                userMapper.getActiveAdminByUsername(request.username)
            }
            else -> {
                userMapper.getActiveTenantByUsername(request.username)
            }
        }

        if (user == null || request.password != jasyptService.encryptor(user.password, false)) {
            throw AppException(AUTH_ERROR)
        }

        val token = Token()
        token.code = jwtService.generateToken(user)
        token.userId = user.id
        token.type = TokenType.NON_OTP
        token.expireDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())

        tokenMapper.insertToken(token)

        val response = LoginResponse()
        response.accessToken = token.code
        response.username = user.username
        response.role = user.role

        baseResponse.setSuccess(response)

        return baseResponse
    }

    fun register(request: RegisterRequest): BaseResponse<RegisterResponse> {
        val baseResponse = BaseResponse<RegisterResponse>()

        request.validate()

        if (userMapper.getActiveUser(request.username, request.email) != null) {
            throw AppException(USERNAME_EXIST)
        }

        val user = User()
        user.username = request.username
        user.fullName = request.fullName
        user.email = request.email
        user.mobile = request.mobile
        user.password = jasyptService.encryptor(request.password, true)
        user.role = request.role!!
        user.credentialNo = request.credential!!.credentialNo
        user.credentialType = request.credential.credentialType
        user.createdDate = Date()

        if (request.role == UserRole.TENANT_MANAGER) {
            val company = Company()
            BeanUtils.copyProperties(request.company!!, company)

            companyMapper.insertCompany(company)

            user.companyId = company.id
        }

        userMapper.insertUser(user)

        val otp = Otp()
        otp.code = generateOTP()
        otp.userId = user.id

        emailService.sendOtpEmail(user.email, otp.code)

        otpMapper.insertOtp(otp)

        val token = Token()
        token.code = jwtService.generateToken(user)
        token.userId = user.id
        token.type = TokenType.OTP
        token.expireDate = Date.from(LocalDateTime.now().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant())

        tokenMapper.insertToken(token)

        val response = RegisterResponse()
        response.otpToken = token.code

        baseResponse.setSuccess(response)

        return baseResponse
    }

    fun verifyOtp(otpCode: String): BaseResponse<RegisterResponse> {
        val otp = otpMapper.getUnverifiedOtp(otpCode)
        val token = tokenMapper.getOtpToken(CurrentUser.get().token)

        if (otp == null || token == null || !token.isValid()) {
            throw AppException(INVALID_OTP_TOKEN)
        }

        val user = token.userId.let { userMapper.getInactiveUser(it) }

        userMapper.activateUser(user!!.id)
        otpMapper.deactivateOtp(otp.id)
        tokenMapper.deactivateToken(token.id)

        return BaseResponse()
    }

    fun logout(): BaseResponse<RegisterResponse> {
        val token = tokenMapper.getToken(CurrentUser.get().token)

        if (token == null) {
            throw AppException(ResponseCode.NOT_FOUND)
        } else {
            // tokenMapper.delete(token)
        }

        return BaseResponse()
    }
}
