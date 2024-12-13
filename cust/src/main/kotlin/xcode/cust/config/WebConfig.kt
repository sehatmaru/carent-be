package xcode.cust.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import xcode.cust.interceptor.UserInterceptor

@Configuration
class WebConfig(
    private val userInterceptor: UserInterceptor,
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:4200",
                "http://localhost:4300",
                "http://localhost:64626/",
                "http://localhost:8007",
                "https://tenant.xcodeid.uk/",
                "https://tenant-admin.xcodeid.uk/",
                "https://carent-admin.onrender.com/",
            )
            .allowedMethods("*")
            .allowedHeaders("*")
            .exposedHeaders("Authorization")
            .allowCredentials(true)
            .maxAge(3600)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userInterceptor).excludePathPatterns(
            "/auth/login",
            "/auth/register",
        )
    }
}
