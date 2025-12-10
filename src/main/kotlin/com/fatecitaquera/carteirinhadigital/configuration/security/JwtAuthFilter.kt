package com.fatecitaquera.carteirinhadigital.configuration.security

import com.fatecitaquera.carteirinhadigital.mappers.SecretaryMapper
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.SecretaryRepository
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import com.fatecitaquera.carteirinhadigital.services.security.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val tokenService: TokenService,
    private val studentRepository: StudentRepository,
    private val secretaryRepository: SecretaryRepository,
    private val studentMapper: StudentMapper,
    private val secretaryMapper: SecretaryMapper
): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null) {
            val token = tokenService.extractToken(authHeader)
            val username = tokenService.extractUsername(token)
            val role = tokenService.extractRole(token)
            val expired = tokenService.isTokenExpired(token)

            if (!expired) {
                val userDetails = when (role) {
                    "STUDENT" -> studentMapper.toUserDomain(studentRepository.findByEmail(username).orElseThrow {
                        throw BadCredentialsException("Student E-mail not found")
                    })
                    "SECRETARY" -> secretaryMapper.toUserDomain(secretaryRepository.findByEmail(username).orElseThrow {
                        throw BadCredentialsException("Secretary E-mail not found")
                    })
                    else -> null
                }
                if (userDetails != null) {
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}