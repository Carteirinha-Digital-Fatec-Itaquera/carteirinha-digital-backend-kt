package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.RecoveryPasswordSecretaryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RecoveryPasswordSecretaryRepository: JpaRepository<RecoveryPasswordSecretaryEntity, String> {
    fun findBySecretary_Email(email: String): Optional<RecoveryPasswordSecretaryEntity>
}