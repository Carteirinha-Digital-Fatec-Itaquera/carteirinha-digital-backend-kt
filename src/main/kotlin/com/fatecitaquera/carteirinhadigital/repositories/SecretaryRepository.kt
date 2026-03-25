package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.SecretaryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SecretaryRepository: JpaRepository<SecretaryEntity, String> {
    fun findByEmail(email: String): Optional<SecretaryEntity>
}