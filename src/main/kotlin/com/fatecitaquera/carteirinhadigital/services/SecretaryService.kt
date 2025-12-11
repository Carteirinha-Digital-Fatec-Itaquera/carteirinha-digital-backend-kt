package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.SecretaryDomain
import com.fatecitaquera.carteirinhadigital.mappers.SecretaryMapper
import com.fatecitaquera.carteirinhadigital.repositories.SecretaryRepository
import org.springframework.stereotype.Service

@Service
class SecretaryService(
    val repository: SecretaryRepository,
    val mapper: SecretaryMapper
) {
    fun create(student: SecretaryDomain) {
        repository.save(mapper.toEntity(student))
    }
}