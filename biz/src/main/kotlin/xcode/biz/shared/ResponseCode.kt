package xcode.biz.shared

object ResponseCode {
    // ERROR MESSAGE
    const val FAILED: String = "Bad request"
    const val NOT_FOUND: String = "Data not found"
    const val PARAMS_ERROR: String = "Wrong params"
    const val EXIST: String = "Data exist"
    const val USERNAME_EXIST: String = "Username or email exist"
    const val UNAUTHORIZED: String = "Not Authorized"
    const val AUTH_ERROR: String = "Wrong username/password"
    const val TOKEN_ERROR: String = "Invalid token"
    const val INVALID_OTP_TOKEN: String = "Invalid token/otp"
    const val INVALID_PASSWORD: String = "Invalid password"

    // SUCCESS
    const val SUCCESS: String = "Success"
}
