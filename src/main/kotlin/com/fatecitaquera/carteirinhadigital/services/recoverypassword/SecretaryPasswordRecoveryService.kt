package com.fatecitaquera.carteirinhadigital.services.recoverypassword

import com.fatecitaquera.carteirinhadigital.domains.RecoveryPasswordSecretaryDomain
import com.fatecitaquera.carteirinhadigital.exceptions.OperationNotAllowedException
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.SecretaryMapper
import com.fatecitaquera.carteirinhadigital.repositories.SecretaryRepository
import com.fatecitaquera.carteirinhadigital.mappers.RecoveryPasswordPersistenceMapper
import com.fatecitaquera.carteirinhadigital.repositories.RecoveryPasswordSecretaryRepository
import com.fatecitaquera.carteirinhadigital.services.EmailService
import com.fatecitaquera.carteirinhadigital.utils.emailContentRecoveryPassword
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SecretaryPasswordRecoveryService(
    private val secretaryRepository: SecretaryRepository,
    private val secretaryMapper: SecretaryMapper,
    private val recoveryPasswordRepository: RecoveryPasswordSecretaryRepository,
    private val recoveryPasswordMapper: RecoveryPasswordPersistenceMapper,
    private val emailService: EmailService
) {
    fun getCode(email: String) {
        val token = String.format("%06d", (0..999999).random())
        recoveryPasswordRepository.findBySecretary_Email(email).ifPresentOrElse(
            {
                val recoveryPassword = recoveryPasswordMapper.toDomain(it)
                recoveryPassword.token = token
                recoveryPassword.moment = LocalDateTime.now().plusMinutes(5)
                recoveryPasswordRepository.save(recoveryPasswordMapper.toEntity(recoveryPassword))
            },
            {
                val secretary = secretaryRepository.findByEmail(email).orElse(null)
                if (secretary != null) {
                    val recoveryPassword = RecoveryPasswordSecretaryDomain(
                        token = token,
                        secretary = secretaryMapper.toDomain(secretary),
                        moment = LocalDateTime.now().plusMinutes(5)
                    )
                    recoveryPasswordRepository.save(recoveryPasswordMapper.toEntity(recoveryPassword))
                }
            }
        )
        emailService.sendEmail(email, "Recuperação de Senha", emailContentRecoveryPassword(token))
    }

    fun changePassword(email: String, token: String, newPassword: String) {

        if (!validateToken(email, token)) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0006)
        }

        val secretary = secretaryMapper.toDomain(secretaryRepository.findByEmail(email).orElseThrow {
            throw ResourceNotFoundException(RuntimeErrorEnum.ERR0007)
        })
        secretary.passwd = BCryptPasswordEncoder().encode(newPassword)!!
        secretaryRepository.save(secretaryMapper.toEntity(secretary))

        val recoveryPassword = recoveryPasswordRepository.findBySecretary_Email(email).orElseThrow {
            throw ResourceNotFoundException(RuntimeErrorEnum.ERR0007)
        }
        recoveryPasswordRepository.delete(recoveryPassword)
    }

    fun validateToken(email: String, token: String): Boolean {
        val recoveryPassword = recoveryPasswordRepository.findBySecretary_Email(email).orElse(null) ?: return false

        if (recoveryPassword.moment.isBefore(LocalDateTime.now())) {
            recoveryPasswordRepository.delete(recoveryPassword)
            return false
        }

        val result = recoveryPassword.token == token
        if (!result) {
            recoveryPasswordRepository.delete(recoveryPassword)
        }
        return result
    }
}
