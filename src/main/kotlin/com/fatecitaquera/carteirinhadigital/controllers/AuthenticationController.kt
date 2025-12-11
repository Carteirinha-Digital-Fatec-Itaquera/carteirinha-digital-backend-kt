package com.fatecitaquera.carteirinhadigital.controllers

import com.fatecitaquera.carteirinhadigital.domains.UserDomain
import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import com.fatecitaquera.carteirinhadigital.services.security.TokenService
import com.fatecitaquera.carteirinhadigital.services.security.UserDetailsService
import com.imobly.imobly.controllers.authentication.dtos.AuthDTO
import com.fatecitaquera.carteirinhadigital.dto.TokenDTO
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/autenticacoes")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService
) {

    @PostMapping("/estudante/logar")
    fun signInTenant(@Valid @RequestBody auth: AuthDTO): ResponseEntity<TokenDTO> {
        userDetailsService.role = UserRoleEnum.STUDENT
        val authenticate = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(auth.email, auth.password)
        )
        val user = authenticate.principal as UserDomain
        return ResponseEntity.ok(generateToken(user.email, user.role, user.id ?: ""))
    }

    @PostMapping("/secretaria/logar")
    fun signInLandLord(@Valid @RequestBody auth: AuthDTO):  ResponseEntity<TokenDTO> {
        userDetailsService.role = UserRoleEnum.SECRETARY
        val authenticate = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(auth.email, auth.password)
        )
        val user = authenticate.principal as UserDomain
        return ResponseEntity.ok(generateToken(user.email, user.role, user.id ?: ""))
    }

    private fun generateToken(email: String, role: UserRoleEnum, id: String): TokenDTO {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val date = calendar.time
        val token = tokenService.generateToken(
            subject = email,
            expiration = date,
            additionalClaims = mapOf(Pair("role", role), Pair("id", id))
        )
        return TokenDTO(token)
    }
}