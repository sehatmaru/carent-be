package xcode.tenant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["xcode.biz", "xcode.tenant"])
@EntityScan(basePackages = ["xcode.biz.domain"])
@EnableJpaRepositories(basePackages = ["xcode.biz.domain.repository"])
class TenantApplication

fun main(args: Array<String>) {
    runApplication<TenantApplication>(*args)
}
