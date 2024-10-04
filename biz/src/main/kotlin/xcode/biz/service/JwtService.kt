package xcode.biz.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import xcode.biz.domain.model.User
import xcode.biz.utils.CommonUtil.getTomorrowDate
import java.util.*

@Service
class JwtService {

    @Value("\${JWT_SIGNING_KEY}")
    lateinit var signingKey: String

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.id.toString())
            .setIssuedAt(Date())
            .setExpiration(getTomorrowDate())
            .signWith(SignatureAlgorithm.HS256, signingKey)
            .compact()
    }
}