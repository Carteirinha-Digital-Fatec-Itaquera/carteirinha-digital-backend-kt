package com.fatecitaquera.carteirinhadigital.services.recoverypassword

import com.fatecitaquera.carteirinhadigital.domains.RecoveryPasswordStudentDomain
import com.fatecitaquera.carteirinhadigital.exceptions.OperationNotAllowedException
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import com.fatecitaquera.carteirinhadigital.services.EmailService
import com.fatecitaquera.carteirinhadigital.repositories.RecoveryPasswordStudentRepository
import com.fatecitaquera.carteirinhadigital.mappers.RecoveryPasswordPersistenceMapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StudentPasswordRecoveryService (
    private val studentRepository: StudentRepository,
    private val studentMapper: StudentMapper,
    private val recoveryPasswordRepository: RecoveryPasswordStudentRepository,
    private val recoveryPasswordMapper: RecoveryPasswordPersistenceMapper,
    private val emailService: EmailService
) {
    fun getCode(email: String) {
        val token =  String.format("%06d", (0..999999).random())
        recoveryPasswordRepository.findByStudent_Email(email).ifPresentOrElse(
            {
                val recoveryPassword = recoveryPasswordMapper.toDomain(it)
                recoveryPassword.token = token
                recoveryPassword.moment = LocalDateTime.now().plusMinutes(5)
                recoveryPasswordRepository.save(recoveryPasswordMapper.toEntity(recoveryPassword))
            },
            {
                val tenant = studentRepository.findByEmail(email).orElse(null)
                if (tenant != null) {
                    val recoveryPassword = RecoveryPasswordStudentDomain(
                        token = token,
                        student = studentMapper.toDomain(tenant),
                        moment = LocalDateTime.now().plusMinutes(5)
                    )
                    recoveryPasswordRepository.save(recoveryPasswordMapper.toEntity(recoveryPassword))
                }
            }
        )
        emailService.sendEmail(email, "Recuperar Senha", "Código de confirmação: $token")
    }

    fun changePassword(email: String, token: String, newPassword: String) {

        if ( !validateToken(email, token) ) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0006)
        }

        val tenant = studentMapper.toDomain(studentRepository.findByEmail(email).orElseThrow {
            throw ResourceNotFoundException(RuntimeErrorEnum.ERR0007)
        })
        tenant.passwd = BCryptPasswordEncoder().encode(newPassword)!!
        studentRepository.save(studentMapper.toEntity(tenant))

        val recoveryPassword = recoveryPasswordRepository.findByStudent_Email(email).orElseThrow {
            throw ResourceNotFoundException(RuntimeErrorEnum.ERR0007)
        }
        recoveryPasswordRepository.delete(recoveryPassword)
    }

    fun validateToken(email: String, token: String): Boolean {
        val recoveryPassword = recoveryPasswordRepository.findByStudent_Email(email).orElse(null) ?: return false

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