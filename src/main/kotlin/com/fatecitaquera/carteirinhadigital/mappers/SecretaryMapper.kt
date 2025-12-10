package com.fatecitaquera.carteirinhadigital.mappers

import com.fatecitaquera.carteirinhadigital.domains.SecretaryDomain
import com.fatecitaquera.carteirinhadigital.domains.UserDomain
import com.fatecitaquera.carteirinhadigital.dto.CreateSecretaryDTO
import com.fatecitaquera.carteirinhadigital.entities.SecretaryEntity
import org.springframework.stereotype.Component

@Component
class SecretaryMapper {
    fun toEntity(secretary: CreateSecretaryDTO): SecretaryEntity =
        SecretaryEntity(
            name = secretary.name,
            email = secretary.email,
            password = secretary.password
        )

    fun toDomain(secretary: SecretaryEntity): SecretaryDomain =
        SecretaryDomain(
            id = secretary.id,
            name = secretary.name,
            email = secretary.email,
            password = secretary.password,
            role = secretary.role
        )

    fun toUserDomain(secretary: SecretaryEntity): UserDomain =
        UserDomain(
            id = secretary.id,
            name = secretary.name,
            email = secretary.email,
            passwd = secretary.password,
            role = secretary.role
        )
}