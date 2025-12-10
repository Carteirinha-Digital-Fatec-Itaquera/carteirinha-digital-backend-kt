package com.fatecitaquera.carteirinhadigital.dto.secretary

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateSecretaryDTO(

    @field:NotBlank(message = "O campo nome é obrigatório")
    val name: String,

    @field:NotBlank(message = "O campo e-mail é obrigatório")
    @field:Email(message = "O campo e-mail está inválido")
    val email: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    @field:Size(max = 50, min = 8, message = "O campo senha deve ter entre 8 e 50 caracteres")
    val password: String

)