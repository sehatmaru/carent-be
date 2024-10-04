package xcode.tenant.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun jwtFilter(): FilterRegistrationBean<WebFilter> {
        val filter = FilterRegistrationBean(WebFilter())
        filter.addUrlPatterns("/v1/*")
        filter.order = 1

        return filter
    }

}
