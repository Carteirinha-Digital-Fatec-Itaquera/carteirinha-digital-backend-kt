package com.fatecitaquera.carteirinhadigital.entities

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "tb_estudante")
class StudentEntity(
    @Id
    val ra: String,

    @Column(name = "curso", nullable = false)
    val course: String,

    @Column(name = "periodo", nullable = false)
    val period: String,

    @Column(nullable = false)
    val status: String,

    @Column(name = "nome_completo", nullable = false)
    val name: String,

    @Column(name = "ingresso", nullable = false)
    val admission: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(unique = true, nullable = false)
    val cpf: String,

    @Column(unique = true, nullable = false)
    val rg: String,

    val qrcode: String?,

    @Column(name = "imagem")
    val photo: String?,

    @Column(name = "data_nascimento", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "data_vencimento", nullable = false)
    val dueDate: LocalDate,

    @Column(name = "senha")
    val password: String,

    @Column(name = "papel", nullable = false)
    val role: UserRoleEnum = UserRoleEnum.STUDENT
)
