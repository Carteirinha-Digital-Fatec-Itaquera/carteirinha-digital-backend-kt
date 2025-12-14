package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.FirstAccessStudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface FirstAccessStudentRepository: JpaRepository<FirstAccessStudentEntity, String> {
    fun findByStudent_Cpf(cpf: String): Optional<FirstAccessStudentEntity>
    fun deleteAllByStudent_Id(id: String)
}