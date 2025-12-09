package com.fatecitaquera.carteirinhadigital.dto

import jakarta.validation.constraints.*
import java.time.LocalDate

data class CreateStudentDTO(

    @field:NotBlank(message = "O campo RA é obrigatório")
    val ra: String,

    @field:NotBlank(message = "O campo curso é obrigatório")
    val course: String,

    @field:NotBlank(message = "O campo periodo é obrigatório")
    val period: String,

    @field:NotBlank(message = "O campo status é obrigatório")
    val status: String,

    @field:NotBlank(message = "O campo nome é obrigatório")
    val name: String,

    @field:NotBlank(message = "O campo ingresso é obrigatório")
    val admission: String,

    @field:NotBlank(message = "O campo e-mail é obrigatório")
    @field:Email(message = "O campo e-mail está inválido")
    val email: String,

    @field:NotBlank(message = "O campo CPF é obrigatório")
    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
        message = "CPF deve estar no formato 000.000.000-00"
    )
    val cpf: String,

    @field:NotBlank(message = "O campo RG é obrigatório")
    @field:Pattern(
        regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$",
        message = "RG deve estar no formato 00.000.000-0"
    )
    val rg: String,

    @field:NotNull(message = "O campo data de nascimento é obrigatório")
    val birthDate: LocalDate,

    @field:NotNull(message = "O campo data de vencimento é obrigatório")
    val dueDate: LocalDate,
)
