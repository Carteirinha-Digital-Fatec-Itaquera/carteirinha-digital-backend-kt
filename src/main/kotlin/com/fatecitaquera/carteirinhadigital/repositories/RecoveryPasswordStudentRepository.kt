package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.RecoveryPasswordStudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RecoveryPasswordStudentRepository: JpaRepository<RecoveryPasswordStudentEntity, String> {
    fun findByStudent_Email(email: String): Optional<RecoveryPasswordStudentEntity>
}