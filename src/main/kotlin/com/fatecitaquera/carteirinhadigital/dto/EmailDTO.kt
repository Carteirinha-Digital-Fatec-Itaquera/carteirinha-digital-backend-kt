package com.fatecitaquera.carteirinhadigital.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class EmailDTO(
    @field:NotNull(message = "O campo E-mail é obrigatório")
    val email: String? = ""
)
