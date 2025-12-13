package com.fatecitaquera.carteirinhadigital.dto

import jakarta.validation.constraints.NotNull

data class AuthDTO(
    val email: String? = "",

    val password: String? = "",
)