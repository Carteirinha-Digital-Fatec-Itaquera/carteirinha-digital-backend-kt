package com.fatecitaquera.carteirinhadigital.exceptions.enums

enum class RuntimeErrorEnum(
    val code: String,
    val description: String
) {
    ERR0001("RESOURCE_NOT_FOUND", "Não possível encontrar este estudante"),
    ERR0002("ARGUMENTS_INVALID", "Há campo(s) inválido(s)"),
    ERR0003("AUTHENTICATION_FAILED", "Não foi possível validar o token"),
    ERR0004("AUTHENTICATION_FAILED", "O E-mail ou a senha estão incorretos"),
}