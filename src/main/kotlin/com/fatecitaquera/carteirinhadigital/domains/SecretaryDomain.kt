package com.fatecitaquera.carteirinhadigital.domains

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum

class SecretaryDomain(
    id: String? = null,
    name: String,
    email: String,
    passwd: String,
    role: UserRoleEnum = UserRoleEnum.SECRETARY
): UserDomain(id, name, email, passwd, role)