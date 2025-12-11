package com.fatecitaquera.carteirinhadigital.repositories

import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface StudentRepository : JpaRepository<StudentEntity, String> {

    fun findAllByNameContainingOrCpfContainingOrRgContainingOrEmailContainingOrCourseContainingOrPeriodContainingOrStatusContainingAllIgnoreCase(
        name: String, cpf: String, rg: String, email: String, course: String, period: String, status: String
    ): List<StudentEntity>

    fun findByRa(ra: String): Optional<StudentEntity>

    fun findByEmail(email: String): Optional<StudentEntity>

}