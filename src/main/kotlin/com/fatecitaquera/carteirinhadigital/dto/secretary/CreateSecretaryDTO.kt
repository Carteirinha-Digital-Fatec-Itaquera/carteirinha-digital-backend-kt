package com.fatecitaquera.carteirinhadigital.dto.secretary

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateSecretaryDTO(

    @field:NotBlank(message = "O campo nome é obrigatório")
    @field:Size(max = 50, min = 3, message = "O campo nome deve ter entre 3 e 50 caracteres")
    val name: String,

    @field:NotBlank(message = "O campo e-mail é obrigatório")
    @field:Email(message = "O campo e-mail está inválido")
    @field:Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres")
    val email: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    @field:Size(max = 50, min = 8, message = "O campo senha deve ter entre 8 e 50 caracteres")
    val password: String

)