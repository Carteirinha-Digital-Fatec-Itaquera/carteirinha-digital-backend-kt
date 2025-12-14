package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.exceptions.DuplicateResourceException
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.RecoveryPasswordStudentRepository
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val repository: StudentRepository,
    private val studentMapper: StudentMapper,
    private val recoveryPasswordStudentRepository: RecoveryPasswordStudentRepository,
) {

    fun findAllByQuery(query: String): List<StudentDomain> =
        studentMapper.toListDomain(
            repository.findAllByNameContainingOrCpfContainingOrRgContainingOrEmailContainingOrCourseContainingOrPeriodContainingOrStatusContainingOrRaContainingAllIgnoreCase(
                query, query, query, query, query, query, query, query
            )
        )

    fun findById(id: String): StudentDomain =
        studentMapper.toDomain(repository.findById(id).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

    fun create(student: StudentDomain) {
        student.id = null
        checkUniqueFields(student)
        repository.save(studentMapper.toEntity(student))
    }

    fun update(id: String, studentWithNewData: StudentDomain) {
        val studentToUpdate = studentMapper.toDomain(repository.findById(id).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

        checkUniqueFields(studentWithNewData, studentToUpdate.id ?: "")

        studentToUpdate.ra = studentWithNewData.ra
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

        repository.save(studentMapper.toEntity(studentToUpdate))
    }

    fun delete(id: String) {
        val studentToDelete = repository.findById(id).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        }
        recoveryPasswordStudentRepository.deleteAllByStudent_Id(studentToDelete.id ?: "")
        repository.delete(studentToDelete)
    }

    private fun checkUniqueFields(student: StudentDomain, id: String = "") {
        if (repository.existsByRaAndIdNot(student.ra, id)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0009)
        }
        if (repository.existsByEmailAndIdNot(student.email, id)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0010)
        }
        if (repository.existsByCpfAndIdNot(student.cpf, id)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0011)
        }
        if (repository.existsByRgAndIdNot(student.rg, id)) {
            throw DuplicateResourceException(RuntimeErrorEnum.ERR0012)
        }
    }
}