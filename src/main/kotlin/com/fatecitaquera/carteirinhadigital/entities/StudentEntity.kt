package com.fatecitaquera.carteirinhadigital.entities

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "tb_estudante")
class StudentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(name = "ra", nullable = false, length = 13)
    val ra: String,

    @Column(name = "curso", nullable = false, length = 100)
    val course: String,

    @Column(name = "periodo", nullable = false, length = 50)
    val period: String,

    @Column(nullable = false, length = 50)
    val status: String,

    @Column(name = "nome_completo", nullable = false, length = 100)
    val name: String,

    @Column(name = "ingresso", nullable = false, length = 50)
    val admission: String,

    @Column(unique = true, nullable = false, length = 100)
    val email: String,

    @Column(unique = true, nullable = false, length = 14)
    val cpf: String,

    @Column(unique = true, nullable = false, length = 12)
    val rg: String,

    val qrcode: String?,

    @Column(name = "imagem")
    val photo: String?,

    @Column(name = "data_nascimento", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "data_vencimento", nullable = false)
    val dueDate: LocalDate,

    @Column(name = "senha")
    val password: String?,

    @Column(name = "papel", nullable = false)
    val role: UserRoleEnum = UserRoleEnum.STUDENT,

    @Column(name = "solicitacao_pendente")
    val requestPending: Boolean,

    @Column(name = "imagem_para_analise")
    val photoForAnalysis: String?
)
