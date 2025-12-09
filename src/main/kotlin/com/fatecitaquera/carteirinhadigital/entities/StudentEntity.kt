package com.fatecitaquera.carteirinhadigital.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "tb_estudante")
class StudentEntity(
    @Id
    var ra: String,

    @Column(name = "curso", nullable = false)
    var course: String,

    @Column(name = "periodo", nullable = false)
    var period: String,

    @Column(nullable = false)
    var status: String,

    @Column(name = "nome_completo", nullable = false)
    var name: String,

    @Column(name = "ingresso", nullable = false)
    var admission: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(unique = true, nullable = false)
    var cpf: String,

    @Column(unique = true, nullable = false)
    var rg: String,

    var qrcode: String?,

    @Column(name = "imagem")
    var photo: String?,

    @Column(name = "data_nascimento", nullable = false)
    var birthDate: LocalDate,

    @Column(name = "data_vencimento", nullable = false)
    var dueDate: LocalDate,

    @Column(name = "senha")
    var password: String?
)
