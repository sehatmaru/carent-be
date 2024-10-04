package xcode.carent.domain.model

import xcode.carent.exception.AppException
import xcode.carent.shared.ResponseCode

object CurrentAuth {

    private val USER_HOLDER: ThreadLocal<Token> = ThreadLocal<Token>()

    @Synchronized
    fun set(tokenDto: Token) {
        USER_HOLDER.set(tokenDto)
    }

    fun get(): Token {
        try {
            return USER_HOLDER.get()
        } catch (e : Exception) {
            throw AppException(ResponseCode.TOKEN_ERROR_MESSAGE)
        }
    }

    @Synchronized
    fun remove() {
        USER_HOLDER.remove()
    }
}