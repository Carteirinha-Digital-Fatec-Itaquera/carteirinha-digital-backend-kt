package com.fatecitaquera.carteirinhadigital.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "tb_recuperacao_senha_estudante")
class RecoveryPasswordStudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String?,

    @OneToOne
    @JoinColumn(name = "fk_estudante_id", referencedColumnName = "ra")
    val student: StudentEntity,

    @Column(name = "codigo_recuperacao", nullable = false, length = 6)
    val token: String,

    @Column(name = "momento", nullable = false)
    val moment: LocalDateTime
)