package xcode.biz.domain.dto

import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode

object CurrentUser {

    private val UserToken_HOLDER: ThreadLocal<UserToken> = ThreadLocal<UserToken>()

    @Synchronized
    fun set(userToken: UserToken) {
        UserToken_HOLDER.set(userToken)
    }

    fun get(): UserToken {
        try {
            return UserToken_HOLDER.get()
        } catch (e: Exception) {
            throw AppException(ResponseCode.TOKEN_ERROR)
        }
    }

    @Synchronized
    fun remove() {
        UserToken_HOLDER.remove()
    }
}
