package com.fatecitaquera.carteirinhadigital.services.security

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.domains.UserDomain
import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import com.fatecitaquera.carteirinhadigital.mappers.SecretaryMapper
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.SecretaryRepository
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    private val studentRepository: StudentRepository,
    private val secretaryRepository: SecretaryRepository,
    private val studentMapper: StudentMapper,
    private val secretaryMapper: SecretaryMapper
): UserDetailsService {

    var role = UserRoleEnum.STUDENT

    override fun loadUserByUsername(username: String): UserDetails {
        if (role == UserRoleEnum.STUDENT) {
            val student = studentRepository.findByEmail(username)
            var user: UserDomain = StudentDomain()
            student.ifPresentOrElse(
                {
                    if (!studentPasswordAlreadyDefined(it)) {
                        throw BadCredentialsException("Student password is not defined")
                    }
                    user = studentMapper.toUserDomain(it)
                },
                {
                    throw BadCredentialsException("Student e-mail not found")
                }
            )
            return user
        }
        val secretary = secretaryRepository.findByEmail(username)
        var user: UserDomain = StudentDomain()
        secretary.ifPresentOrElse(
            {
                user = secretaryMapper.toUserDomain(it)
            },
            {
                throw BadCredentialsException("Secretary e-mail not found")
            }
        )
        return user
    }

    private fun studentPasswordAlreadyDefined(student: StudentEntity): Boolean {
        return student.password != null && student.password!!.isNotBlank()
    }
}