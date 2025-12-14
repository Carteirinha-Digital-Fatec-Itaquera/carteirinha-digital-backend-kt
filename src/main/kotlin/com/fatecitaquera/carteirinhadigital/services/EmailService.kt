package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.exceptions.InternalErrorException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val mailSender: JavaMailSender
) {

    fun sendEmail(to: String, subject: String, content: String) {
        try {
            val message: MimeMessage = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true, "UTF-8")

            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(content, true)

            mailSender.send(message)
        } catch (ex: MailException) {
            throw InternalErrorException(RuntimeErrorEnum.ERR0005)
        }
    }
}