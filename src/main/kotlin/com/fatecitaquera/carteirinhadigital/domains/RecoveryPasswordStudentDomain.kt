package com.fatecitaquera.carteirinhadigital.domains

import java.time.LocalDateTime

class RecoveryPasswordStudentDomain(
    var id: String? = null,

    var student: StudentDomain,

    var token: String,

    var moment: LocalDateTime = LocalDateTime.now()
)