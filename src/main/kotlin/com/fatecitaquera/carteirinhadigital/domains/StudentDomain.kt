package com.fatecitaquera.carteirinhadigital.domains

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import java.time.LocalDate

class StudentDomain(
    ra: String = "",
    name: String = "",
    email: String = "",
    password: String = "",
    role: UserRoleEnum = UserRoleEnum.STUDENT,
    var course: String = "",
    var period: String = "",
    var status: String = "",
    var admission: String = "",
    var cpf: String = "",
    var rg: String = "",
    var qrcode: String? = "",
    var photo: String? = "",
    var birthDate: LocalDate = LocalDate.MIN,
    var dueDate: LocalDate = LocalDate.MIN,
): UserDomain(ra, name, email, password, role)