package xcode.biz.domain.model

import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode

object CurrentAuth {

    private val USER_HOLDER: ThreadLocal<Token> = ThreadLocal<Token>()

    @Synchronized
    fun set(tokenDto: Token) {
        USER_HOLDER.set(tokenDto)
    }

    fun get(): Token {
        try {
            return USER_HOLDER.get()
        } catch (e: Exception) {
            throw AppException(ResponseCode.TOKEN_ERROR_MESSAGE)
        }
    }

    @Synchronized
    fun remove() {
        USER_HOLDER.remove()
    }
}
