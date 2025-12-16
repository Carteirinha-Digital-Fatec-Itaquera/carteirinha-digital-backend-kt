package com.fatecitaquera.carteirinhadigital.exceptions.enums

enum class RuntimeErrorEnum(
    val code: String,
    val description: String
) {
    ERR0001("RESOURCE_NOT_FOUND", "Não possível encontrar este estudante"),
    ERR0002("ARGUMENTS_INVALID", "Há campo(s) inválido(s)"),
    ERR0003("AUTHENTICATION_FAILED", "A autenticação falhou"),
    ERR0004("AUTHENTICATION_FAILED", "O e-mail ou a senha estão incorretos"),
    ERR0005("INTERNAL_ERROR", "Não foi possível enviar o e-mail. Tente novamente mais tarde"),
    ERR0006("OPERATION_NOT_ALLOWED", "O codigo solicitado está incorreto ou foi expirado"),
    ERR0007("RESOURCE_NOT_FOUND", "O e-mail informado não foi encontrado"),
    ERR0008("OPERATION_NOT_ALLOWED", "O sistema permite a criação de somente uma conta de secretaria"),
    ERR0009("DUPLICATE_RESOURCE", "O RA informado já existe"),
    ERR0010("DUPLICATE_RESOURCE", "O e-mail informado já existe"),
    ERR0011("DUPLICATE_RESOURCE", "O CPF informado já existe"),
    ERR0012("DUPLICATE_RESOURCE", "O RG informado já existe"),
    ERR0013("RESOURCE_NOT_FOUND", "O CPF informado não foi encontrado"),
    ERR0014("OPERATION_NOT_ALLOWED", "A senha deve ter ao menos um caracterer maiúsculo, numérico e especial"),
    ERR0015("UPLOAD_OBJECT_ERROR", "O tipo da imagem não é suportada. Escolha entre (JPEG, PNG, WEBP)"),
    ERR0016("UPLOAD_OBJECT_ERROR", "Houve um erro inesperado ao fazer o upload da imagem."),
    ERR0017("UPLOAD_OBJECT_ERROR", "Não foi possível obter a extensão do arquivo"),
    ERR0018("MULTIPART_FILE_ERROR", "A imagem é obrigatória"),

}