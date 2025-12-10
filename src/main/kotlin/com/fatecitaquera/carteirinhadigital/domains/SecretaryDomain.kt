package com.fatecitaquera.carteirinhadigital.domains

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum

class SecretaryDomain(
    id: String? = null,
    name: String,
    email: String,
    password: String,
    role: UserRoleEnum
): UserDomain(id, name, email, password, role)