package com.fatecitaquera.carteirinhadigital.dto.student

import java.time.LocalDate

data class ViewStudentDTO(
    val id: String,
    val ra: String,
    val course: String,
    val period: String,
    val status: String,
    val name: String,
    val admission: String,
    val email: String,
    val cpf: String,
    val rg: String,
    val qrcode: String,
    val photo: String,
    val birthDate: LocalDate,
    val dueDate: LocalDate
)
