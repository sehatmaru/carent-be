package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Token

@Repository
interface TokenRepository : JpaRepository<Token, String> {
    fun findByCode(code: String): Token?

    @Query(
        value = "SELECT * FROM t_token WHERE code = :code AND is_active IS TRUE AND type = 'OTP' LIMIT 1",
        nativeQuery = true,
    )
    fun getOtpToken(@Param("code") code: String): Token?
}
