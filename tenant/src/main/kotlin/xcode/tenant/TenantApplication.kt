package xcode.tenant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["xcode.biz", "xcode.tenant"])
class TenantApplication

fun main(args: Array<String>) {
    runApplication<TenantApplication>(*args)
}
