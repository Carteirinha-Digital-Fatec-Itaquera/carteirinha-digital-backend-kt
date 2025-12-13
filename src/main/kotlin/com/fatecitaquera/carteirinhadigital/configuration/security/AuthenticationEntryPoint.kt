package com.fatecitaquera.carteirinhadigital.configuration.security

import com.fatecitaquera.carteirinhadigital.dto.ErrorMessageDTO
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class JwtAuthEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = "application/json;charset=UTF-8"
        response.characterEncoding = "UTF-8"

        response.writer.write(ErrorMessageDTO(
            RuntimeErrorEnum.ERR0003.name,
            HttpStatus.UNAUTHORIZED.value(),
            RuntimeErrorEnum.ERR0003.description,
            Instant.now(),
            request.requestURI
        ).toString())
    }
}
