package com.fatecitaquera.carteirinhadigital.mappers

import com.fatecitaquera.carteirinhadigital.dto.CreateStudentDTO
import com.fatecitaquera.carteirinhadigital.dto.ViewStudentDTO
import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import org.springframework.stereotype.Component

@Component
class StudentMapper {

    fun toEntity(student: CreateStudentDTO): StudentEntity {
        return StudentEntity(
            ra = student.ra,
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
            password = null
        )
    }

    fun toDTO(student: StudentEntity): ViewStudentDTO {
        return ViewStudentDTO(
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
            dueDate = student.dueDate
        )
    }

    fun toListDTO(students: List<StudentEntity>): List<ViewStudentDTO> {
        return students.map { student -> toDTO(student) }
    }
}
