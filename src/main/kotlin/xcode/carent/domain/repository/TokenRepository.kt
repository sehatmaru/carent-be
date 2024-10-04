package xcode.carent.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xcode.carent.domain.model.Token

@Repository
interface TokenRepository : JpaRepository<Token, String> {
    fun findByToken(token: String): Token?
}