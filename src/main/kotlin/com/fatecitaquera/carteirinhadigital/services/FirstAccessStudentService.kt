package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.FirstAccessStudentDomain
import com.fatecitaquera.carteirinhadigital.exceptions.OperationNotAllowedException
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.FirstAccessPersistenceMapper
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.FirstAccessStudentRepository
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import com.fatecitaquera.carteirinhadigital.utils.emailContentFirstAccess
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FirstAccessStudentService(
    private val studentRepository: StudentRepository,
    private val studentMapper: StudentMapper,
    private val firstAccessStudentRepository: FirstAccessStudentRepository,
    private val firstAccessMapper: FirstAccessPersistenceMapper,
    private val emailService: EmailService
) {
    fun getCode(cpf: String): String {
        val token =  String.format("%06d", (0..999999).random())
        var email: String? = null
        firstAccessStudentRepository.findByStudent_Cpf(cpf).ifPresentOrElse(
            {
                if (it.student.password == null) {
                    val firstAccess = firstAccessMapper.toDomain(it)
                    firstAccess.token = token
                    firstAccess.moment = LocalDateTime.now().plusMinutes(5)
                    email = it.student.email
                    firstAccessStudentRepository.save(firstAccessMapper.toEntity(firstAccess))
                }
            },
            {
                val student = studentRepository.findByCpf(cpf).orElse(null)
                if (student != null && student.password == null) {
                    val firstAccess = FirstAccessStudentDomain(
                        token = token,
                        student = studentMapper.toDomain(student),
                        moment = LocalDateTime.now().plusMinutes(5)
                    )
                    email = student.email
                    firstAccessStudentRepository.save(firstAccessMapper.toEntity(firstAccess))
                }
            }
        )
        if (email != null) {
            emailService.sendEmail(email, "Primeiro acesso", emailContentFirstAccess(token))
        }
        return email ?: ""
    }

    fun changePassword(cpf: String, token: String, newPassword: String) {

        if ( !validateToken(cpf, token) ) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0006)
        }

        val student = studentMapper.toDomain(studentRepository.findByCpf(cpf).orElseThrow {
            throw ResourceNotFoundException(RuntimeErrorEnum.ERR0013)
        })
        student.passwd = BCryptPasswordEncoder().encode(newPassword)!!
        studentRepository.save(studentMapper.toEntity(student))

        val recoveryPassword = firstAccessStudentRepository.findByStudent_Cpf(cpf).orElseThrow {
            throw ResourceNotFoundException(RuntimeErrorEnum.ERR0013)
        }
        firstAccessStudentRepository.delete(recoveryPassword)
    }

    fun validateToken(cpf: String, token: String): Boolean {
        val recoveryPassword = firstAccessStudentRepository.findByStudent_Cpf(cpf).orElse(null) ?: return false

        if (recoveryPassword.moment.isBefore(LocalDateTime.now())) {
            firstAccessStudentRepository.delete(recoveryPassword)
            return false
        }

        val result = recoveryPassword.token == token
        if (!result) {
            firstAccessStudentRepository.delete(recoveryPassword)
        }

        return result
    }
}