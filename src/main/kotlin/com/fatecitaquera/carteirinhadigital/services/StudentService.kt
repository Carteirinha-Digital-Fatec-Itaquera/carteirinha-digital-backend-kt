package com.fatecitaquera.carteirinhadigital.services

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.exceptions.DuplicateResourceException
import com.fatecitaquera.carteirinhadigital.exceptions.InvalidArgumentsException
import com.fatecitaquera.carteirinhadigital.exceptions.ResourceNotFoundException
import com.fatecitaquera.carteirinhadigital.exceptions.enums.RuntimeErrorEnum
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.repositories.RecoveryPasswordStudentRepository
import com.fatecitaquera.carteirinhadigital.repositories.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class StudentService(
    private val repository: StudentRepository,
    private val studentMapper: StudentMapper,
    private val uploadService: UploadService,
    private val recoveryPasswordStudentRepository: RecoveryPasswordStudentRepository,
) {

    fun findAllByQuery(query: String): List<StudentDomain> {
        val students = studentMapper.listEntityToListDomain(
            repository.findAllByNameContainingOrCpfContainingOrRgContainingOrEmailContainingOrCourseContainingOrPeriodContainingOrStatusContainingOrRaContainingAllIgnoreCase(
                query, query, query, query, query, query, query, query
            )
        )

        return students.map { student ->
            if (!student.photoApproved) {
                student.photo = ""
            }
            student
        }
    }

    fun findById(id: String): StudentDomain {
        val student = studentMapper.toDomain(repository.findById(id).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })
        if (!student.photoApproved) {
            student.photo = ""
        }
        return student
    }

    fun create(student: StudentDomain) {
        student.id = null
        checkUniqueFields(student)
        repository.save(studentMapper.toEntity(student))
    }

    fun createMany(students: List<StudentDomain>) {
        val studentEntities = students.map { student ->
            student.id = null
            checkUniqueFields(student)
            studentMapper.toEntity(student)
        }
        repository.saveAll(studentEntities)
    }

    fun uploadPhotoAndLinkWithStudent(id: String, file: MultipartFile?) {
        val studentToUpdate = studentMapper.toDomain(repository.findById(id).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

        uploadService.checkIfMultipartFileIsNull( file )

        studentToUpdate.photo = uploadService.uploadImage( file!! )
        studentToUpdate.photoApproved = false
        studentToUpdate.requestPending = true

        repository.save(studentMapper.toEntity(studentToUpdate))
    }

    fun approvePhoto(id: String, approved: Boolean) {
        val studentToUpdate = studentMapper.toDomain(repository.findById(id).orElseThrow {
            ResourceNotFoundException(RuntimeErrorEnum.ERR0001)
        })

        studentToUpdate.photoApproved = approved
        studentToUpdate.requestPending = false

        repository.save(studentMapper.toEntity(studentToUpdate))
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