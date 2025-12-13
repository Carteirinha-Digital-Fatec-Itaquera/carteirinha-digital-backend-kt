package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.exceptions.DuplicateResourceException
import com.fatecitaquera.carteirinhadigital.exceptions.OperationNotAllowedException
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

    fun findAllByQuery(query: String): List<StudentDomain> =
        mapper.toListDomain(
            repository.findAllByNameContainingOrCpfContainingOrRgContainingOrEmailContainingOrCourseContainingOrPeriodContainingOrStatusContainingAllIgnoreCase(
                query, query, query, query, query, query, query
            )
        )

    fun findByRa(ra: String): StudentDomain =
        mapper.toDomain(repository.findByRa(ra).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

    fun create(student: StudentDomain) {
        checkUniqueFields(student)
        repository.save(mapper.toEntity(student))
    }

    fun update(ra: String, studentWithNewData: StudentDomain) {
        val studentToUpdate = mapper.toDomain(repository.findByRa(ra).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

        checkUniqueFields(studentWithNewData, studentToUpdate.cpf, studentToUpdate.id ?: "")

        studentToUpdate.id = studentWithNewData.id
        studentToUpdate.name = studentWithNewData.name
        studentToUpdate.status = studentWithNewData.status
        studentToUpdate.cpf = studentWithNewData.cpf
        studentToUpdate.rg = studentWithNewData.rg
        studentToUpdate.role = studentWithNewData.role
        studentToUpdate.birthDate = studentWithNewData.birthDate
        studentToUpdate.dueDate = studentWithNewData.dueDate
        studentToUpdate.course = studentWithNewData.course
        studentToUpdate.admission = studentWithNewData.admission
        studentToUpdate.email = studentWithNewData.email
        studentToUpdate.period = studentWithNewData.period

        repository.save(mapper.toEntity(studentToUpdate))
    }

    fun delete(ra: String) {
        val studentToDelete = repository.findByRa(ra).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        }
        repository.delete(studentToDelete)
    }

    private fun checkUniqueFields(student: StudentDomain, cpf: String = "", ra: String = "") {
        if (repository.existsByRaAndCpfNot(student.id ?: "", cpf)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0009)
        }
        if (repository.existsByEmailAndCpfNot(student.email, cpf)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0010)
        }
        if (repository.existsByCpfAndRaNot(student.cpf, ra)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0011)
        }
        if (repository.existsByRgAndCpfNot(student.rg, cpf)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0012)
        }
    }
}