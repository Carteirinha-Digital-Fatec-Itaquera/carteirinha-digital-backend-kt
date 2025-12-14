package com.fatecitaquera.carteirinhadigital.dto

import jakarta.validation.constraints.NotNull

data class EmailDTO(
    @field:NotNull(message = "O campo E-mail é obrigatório")
    val email: String? = ""
)
