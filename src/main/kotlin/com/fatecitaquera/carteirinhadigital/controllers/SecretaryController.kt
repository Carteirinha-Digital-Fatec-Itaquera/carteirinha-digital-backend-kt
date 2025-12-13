package com.fatecitaquera.carteirinhadigital.controllers

import com.fatecitaquera.carteirinhadigital.dto.secretary.CreateSecretaryDTO
import com.fatecitaquera.carteirinhadigital.mappers.SecretaryMapper
import com.fatecitaquera.carteirinhadigital.services.SecretaryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/secretaria")
class SecretaryController (
    val service: SecretaryService,
    val mapper: SecretaryMapper
) {
    @PostMapping("/criar")
    fun create(@Valid @RequestBody student: CreateSecretaryDTO): ResponseEntity<Void> {
        service.create(mapper.toDomain(student))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}