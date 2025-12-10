package com.fatecitaquera.carteirinhadigital.exceptions.enums

enum class ErrorRuntimeEnum(
    val code: String,
    val description: String
) {
    ERR001("RESOURCE_NOT_FOUND", "Não possível encontrar este estudante"),
    ERR002("ARGUMENTS_INVALID", "Há campo(s) inválido(s) ")

}