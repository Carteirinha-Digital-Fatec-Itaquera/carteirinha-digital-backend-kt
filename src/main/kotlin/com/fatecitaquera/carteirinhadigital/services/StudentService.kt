package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.ErrorRuntimeEnum
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(val repository: StudentRepository) {

    fun findAll(): List<StudentEntity> = repository.findAll()


    fun findByRa(ra: String): StudentEntity =
        repository.findByRa(ra).orElseThrow {
            ResourceNotFoundException(ErrorRuntimeEnum.ERR001)
        }

    fun create(student: StudentEntity) {
        repository.save(student)
    }
}