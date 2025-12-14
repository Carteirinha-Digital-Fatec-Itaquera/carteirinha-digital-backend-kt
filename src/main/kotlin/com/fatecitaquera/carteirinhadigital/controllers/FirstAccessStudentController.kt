package com.fatecitaquera.carteirinhadigital.controllers

import com.fatecitaquera.carteirinhadigital.dto.CodeDTO
import com.fatecitaquera.carteirinhadigital.dto.CpfDTO
import com.fatecitaquera.carteirinhadigital.dto.EmailDTO
import com.fatecitaquera.carteirinhadigital.exceptions.OperationNotAllowedException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.services.recoverypassword.StudentPasswordRecoveryService
import com.fatecitaquera.carteirinhadigital.dto.PasswordDTO
import com.fatecitaquera.carteirinhadigital.services.FirstAccessStudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/primeiro-acesso")
class FirstAccessStudentController (
    private val service: FirstAccessStudentService,
) {
    @PostMapping("/solicitar-codigo")
    fun requestCode(@Valid @RequestBody dto: CpfDTO): ResponseEntity<EmailDTO> {
        val email = service.getCode(dto.cpf ?: "")
        return ResponseEntity.status(HttpStatus.OK).body(EmailDTO(email))
    }

    @PostMapping("/validar-token")
    fun validateCode(@Valid @RequestBody dto: CodeDTO): ResponseEntity<Void> {
        if ( !service.validateToken(dto.cpf ?: "", dto.code ?: "") ) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0007)
        }
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PatchMapping("/criar-nova-senha")
    fun resetPassword(@Valid @RequestBody dto: PasswordDTO): ResponseEntity<Void> {
        service.changePassword(dto.cpf ?: "", dto.code ?: "", dto.password ?: "")
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}