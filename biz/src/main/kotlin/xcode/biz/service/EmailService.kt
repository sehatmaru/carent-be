package xcode.biz.service

import jakarta.mail.MessagingException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import xcode.biz.exception.AppException

@Service
class EmailService(private val mailSender: JavaMailSender) {

    fun sendOtpEmail(email: String?, otp: String) {
        try {
            val message = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true)

            helper.setTo(email!!)
            helper.setSubject("OTP Verification")
            val emailContent = "Your OTP: <b>$otp</b>. This OTP is valid for 5 minutes."
            helper.setText(emailContent, true)

            mailSender.send(message)
        } catch (e: MessagingException) {
            throw AppException(e.message)
        }
    }

    fun sendResetPasswordEmail(email: String?, code: String) {
        try {
            val message = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true)

            helper.setTo(email!!)
            helper.setSubject("Reset Password")
            val emailContent =
                "Click <a href=\"https://xcode-ingot.web.app/reset-password?code=$code\">here</a> to reset your password. This link is valid for 5 minutes."
            helper.setText(emailContent, true)

            mailSender.send(message)
        } catch (e: MessagingException) {
            throw AppException(e.message)
        }
    }
}
