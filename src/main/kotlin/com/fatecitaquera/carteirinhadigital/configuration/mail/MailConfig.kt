package com.fatecitaquera.carteirinhadigital.configuration.mail

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.SimpleMailMessage

@Configuration
class MailConfig {
    @Bean
    fun mailMessage(): SimpleMailMessage{
        val message = SimpleMailMessage()
        return message
    }
}