package xcode.biz.service

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JasyptService {

    @Value("\${JASYPT_PASSWORD}")
    lateinit var jasyptPassword: String

    fun encryptor(value: String?, isEncrypt: Boolean): String {
        val jasypt = StandardPBEStringEncryptor()

        jasypt.setPassword(jasyptPassword)

        return if (isEncrypt) jasypt.encrypt(value) else jasypt.decrypt(value)
    }
}
