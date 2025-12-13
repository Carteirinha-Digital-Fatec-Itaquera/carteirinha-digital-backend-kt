package com.fatecitaquera.carteirinhadigital.dto.student

import jakarta.validation.constraints.*
import java.time.LocalDate

data class StudentDTO(

    @field:NotBlank(message = "O campo RA é obrigatório")
    @field:Size(max = 30, message = "O campo RA deve ter no máximo 30 caracteres")
    val ra: String?,

    @field:NotBlank(message = "O campo curso é obrigatório")
    @field:Size(max = 100, message = "O campo curso deve ter no máximo 100 caracteres")
    val course: String?,

    @field:NotBlank(message = "O campo periodo é obrigatório")
    @field:Size(max = 50, message = "O campo periodo deve ter no máximo 50 caracteres")
    val period: String?,

    @field:NotBlank(message = "O campo status é obrigatório")
    @field:Size(max = 50, message = "O campo status deve ter no máximo 50 caracteres")
    val status: String?,

    @field:NotBlank(message = "O campo nome é obrigatório")
    @field:Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres")
    val name: String?,

    @field:NotBlank(message = "O campo ingresso é obrigatório")
    @field:Size(max = 50, message = "O campo ingresso deve ter no máximo 50 caracteres")
    val admission: String?,

    @field:NotBlank(message = "O campo e-mail é obrigatório")
    @field:Email(message = "O campo e-mail está inválido")
    @field:Size(max = 100, message = "O campo e-mail deve ter no máximo 100 caracteres")
    val email: String?,

    @field:NotBlank(message = "O campo CPF é obrigatório")
    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
        message = "CPF deve estar no formato 000.000.000-00"
    )
    val cpf: String?,

    @field:NotBlank(message = "O campo RG é obrigatório")
    @field:Pattern(
        regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$",
        message = "RG deve estar no formato 00.000.000-0"
    )
    val rg: String?,

    @field:NotNull(message = "O campo data de nascimento é obrigatório")
    val birthDate: LocalDate?,

    @field:NotNull(message = "O campo data de vencimento é obrigatório")
    val dueDate: LocalDate?
)
