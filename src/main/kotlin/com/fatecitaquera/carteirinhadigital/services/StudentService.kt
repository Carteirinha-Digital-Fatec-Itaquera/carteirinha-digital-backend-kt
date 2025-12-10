package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(
    val repository: StudentRepository,
    val mapper: StudentMapper
) {

    fun findAll(): List<StudentDomain> =
        mapper.toListDomain(repository.findAll())

    fun findByRa(ra: String): StudentDomain =
        mapper.toDomain(repository.findByRa(ra).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

    fun create(student: StudentDomain) {
        mapper.toDomain(repository.save(mapper.toEntity(student)))
    }
}