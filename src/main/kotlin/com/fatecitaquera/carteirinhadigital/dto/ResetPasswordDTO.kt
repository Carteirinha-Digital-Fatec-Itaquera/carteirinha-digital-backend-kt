package com.fatecitaquera.carteirinhadigital.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ResetPasswordDTO(
    @field:NotNull(message = "O campo E-mail é obrigatório")
    val email: String?,

    @field:NotNull(message = "O campo código é obrigatório")
    val code:String?,

    @field:NotNull(message = "O campo senha é obrigatório")
    @field:Size(min = 8, max = 50, message = "O campo senha deve ter entre 8  e 50 caracteres")
    val newPassword: String?
)
