package xcode.cust

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["xcode.biz", "xcode.cust"])
class CustApplication

fun main(args: Array<String>) {
    runApplication<CustApplication>(*args)
}
