package xcode.tenant.config

import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.filter.GenericFilterBean

@Service
class WebFilter : GenericFilterBean() {

    @Value("\${JWT_SIGNING_KEY}")
    lateinit var signingKey: String

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse
        val uri = request.requestURI

        if (uri.startsWith("/v1")) {
            val authHeader = request.getHeader("Authorization") ?: request.getHeader("authorization")

            if ("OPTIONS" == request.method) {
                response.status = HttpServletResponse.SC_OK
                filterChain.doFilter(request, response)
            } else {
                if (!authHeader.startsWith("Bearer ")) {
                    throw ServletException()
                } else {
                    val token = authHeader.substring(7)

                    Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).body
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}
