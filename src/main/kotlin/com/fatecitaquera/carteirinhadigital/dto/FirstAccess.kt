package com.fatecitaquera.carteirinhadigital.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CpfDTO(
    @field:NotBlank(message = "O campo CPF é obrigatório")
    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
        message = "CPF deve estar no formato 000.000.000-00"
    )
    val cpf: String?
)


data class CodeDTO(
    @field:NotBlank(message = "O campo CPF é obrigatório")
    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
        message = "CPF deve estar no formato 000.000.000-00"
    )
    val cpf: String?,

    @field:NotBlank(message = "O campo código é obrigatório")
    val code:String?

)

data class PasswordDTO(
    @field:NotBlank(message = "O campo CPF é obrigatório")
    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
        message = "CPF deve estar no formato 000.000.000-00"
    )
    val cpf: String?,

    @field:NotBlank(message = "O campo código é obrigatório")
    val code:String?,

    @field:NotNull(message = "O campo senha é obrigatório")
    @field:Size(min = 8, max = 50, message = "O campo senha deve ter entre 8  e 50 caracteres")
    val password: String?
)
