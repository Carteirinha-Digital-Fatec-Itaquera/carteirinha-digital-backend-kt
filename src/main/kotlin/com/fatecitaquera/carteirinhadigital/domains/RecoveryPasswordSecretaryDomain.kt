package com.fatecitaquera.carteirinhadigital.domains

import java.time.LocalDateTime

class RecoveryPasswordSecretaryDomain(
    var id: String? = null,

    var secretary: SecretaryDomain,

    var token: String,

    var moment: LocalDateTime = LocalDateTime.now()
)