package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.SecretaryEntity
import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SecretaryRepository: JpaRepository<SecretaryEntity, String> {
    fun findByEmail(email: String): Optional<SecretaryEntity>
}