package com.fatecitaquera.carteirinhadigital.mappers

import com.fatecitaquera.carteirinhadigital.domains.StudentDomain
import com.fatecitaquera.carteirinhadigital.domains.UserDomain
import com.fatecitaquera.carteirinhadigital.dto.student.CreateStudentDTO
import com.fatecitaquera.carteirinhadigital.dto.student.ViewStudentDTO
import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import org.springframework.stereotype.Component

@Component
class StudentMapper {

    fun toEntity(student: StudentDomain): StudentEntity =
        StudentEntity(
            ra = student.id ?: "",
            course = student.course,
            period = student.period,
            status = student.status,
            name = student.name,
            admission = student.admission,
            email = student.email,
            cpf = student.cpf,
            rg = student.rg,
            qrcode = null,
            photo = null,
            birthDate = student.birthDate,
            dueDate = student.dueDate,
            password = ""
        )

    fun toDomain(student: CreateStudentDTO): StudentDomain =
        StudentDomain(
            ra = student.ra,
            course = student.course,
            period = student.period,
            status = student.status,
            name = student.name,
            admission = student.admission,
            email = student.email,
            cpf = student.cpf,
            rg = student.rg,
            birthDate = student.birthDate,
            dueDate = student.dueDate,
            password = "",
            qrcode = null,
            photo = null
        )

    fun toDomain(student: StudentEntity): StudentDomain =
        StudentDomain(
            ra = student.ra,
            course = student.course,
            period = student.period,
            status = student.status,
            name = student.name,
            admission = student.admission,
            email = student.email,
            cpf = student.cpf,
            rg = student.rg,
            qrcode = student.qrcode ?: "",
            photo = student.photo ?: "",
            birthDate = student.birthDate,
            dueDate = student.dueDate,
            password = student.password,
            role = student.role
        )

    fun toDTO(student: StudentDomain): ViewStudentDTO =
        ViewStudentDTO(
            ra = student.id ?: "",
            course = student.course,
            period = student.period,
            status = student.status,
            name = student.name,
            admission = student.admission,
            email = student.email,
            cpf = student.cpf,
            rg = student.rg,
            qrcode = student.qrcode ?: "",
            photo = student.photo ?: "",
            birthDate = student.birthDate,
            dueDate = student.dueDate
        )

    fun toListDomain(students: List<StudentEntity>): List<StudentDomain> =
        students.map { student -> toDomain(student) }

    fun toListDTO(students: List<StudentDomain>): List<ViewStudentDTO> =
        students.map { student -> toDTO(student) }

    fun toUserDomain(student: StudentEntity): UserDomain =
        UserDomain(
            id = student.ra,
            name = student.name,
            email = student.email,
            passwd = student.password,
            role = student.role
        )
}
