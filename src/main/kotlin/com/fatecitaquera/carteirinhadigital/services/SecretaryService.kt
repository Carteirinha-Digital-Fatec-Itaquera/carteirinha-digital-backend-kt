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
    private val repository: SecretaryRepository,
    private val mapper: SecretaryMapper
) {
    fun signup(secretary: SecretaryDomain) {
        if (repository.findAll().isNotEmpty()) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0008)
        }
        secretary.id = null
        val regex = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$")

        if (!secretary.passwd.matches(regex)) {
            throw OperationNotAllowedException(RuntimeErrorEnum.ERR0014)
        }
        secretary.passwd = BCryptPasswordEncoder().encode(secretary.passwd).toString()
        repository.save(mapper.toEntity(secretary))
    }
}