package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.SecretaryDomain
import com.fatecitaquera.carteirinhadigital.exceptions.OperationNotAllowedException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.SecretaryMapper
import com.fatecitaquera.carteirinhadigital.repositories.SecretaryRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecretaryService(
    val repository: SecretaryRepository,
    val mapper: SecretaryMapper
) {
    fun create(secretary: SecretaryDomain) {
        if (repository.findAll().isNotEmpty()) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0008)
        }
        secretary.id = null
        secretary.passwd = BCryptPasswordEncoder().encode(secretary.passwd).toString()
        repository.save(mapper.toEntity(secretary))
    }
}