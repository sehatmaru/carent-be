package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Token

@Repository
interface TokenRepository : JpaRepository<Token, String> {
    fun findByToken(token: String): Token?
}