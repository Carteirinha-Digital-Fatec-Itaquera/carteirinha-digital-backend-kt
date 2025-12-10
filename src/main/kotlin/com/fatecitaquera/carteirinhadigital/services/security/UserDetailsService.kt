package com.fatecitaquera.carteirinhadigital.services.security

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.domains.UserDomain
import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
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
            val tenant = studentRepository.findByEmail(username)
            var user: UserDomain = StudentDomain()
            tenant.ifPresentOrElse(
                {
                    user = studentMapper.toUserDomain(it)
                },
                {
                    throw BadCredentialsException("Tenant E-mail not found")
                }
            )
            return user
        }
        val landLord = secretaryRepository.findByEmail(username)
        var user: UserDomain = StudentDomain()
        landLord.ifPresentOrElse(
            {
                user = secretaryMapper.toUserDomain(it)
            },
            {
                throw BadCredentialsException("Land Lord E-mail not found")
            }
        )
        return user
    }
}