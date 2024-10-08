package xcode.biz.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class MailConfig {

    @Value("\${MAIL_USERNAME}")
    lateinit var mailUsername: String

    @Value("\${MAIL_PASSWORD}")
    lateinit var mailPassword: String

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587
        mailSender.username = mailUsername
        mailSender.password = mailPassword

        val props = Properties()
        props["mail.smtp.auth"] = true
        props["mail.smtp.starttls.enable"] = true
        props["mail.smtp.ssl.trust"] = "smtp.gmail.com"

        mailSender.javaMailProperties = props
        return mailSender
    }
}
