package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface StudentRepository : JpaRepository<StudentEntity, String> {

    fun findAllByNameContainingOrCpfContainingOrRgContainingOrEmailContainingOrCourseContainingOrPeriodContainingOrStatusContainingOrRaContainingAllIgnoreCase(
        name: String, cpf: String, rg: String, email: String, course: String, period: String, status: String, ra: String
    ): List<StudentEntity>

    fun findByEmail(email: String): Optional<StudentEntity>

    fun findByCpf(cpf: String): Optional<StudentEntity>

    fun existsByRaAndIdNot(ra: String, id: String): Boolean

    fun existsByEmailAndIdNot(email: String, id: String): Boolean

    fun existsByCpfAndIdNot(cpf: String, id: String): Boolean

    fun existsByRgAndIdNot(rg: String, id: String): Boolean
}