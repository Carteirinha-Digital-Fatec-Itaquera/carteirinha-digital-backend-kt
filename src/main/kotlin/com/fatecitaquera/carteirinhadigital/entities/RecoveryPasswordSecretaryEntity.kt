package com.fatecitaquera.carteirinhadigital.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tb_recuperacao_senha_secretaria")
class RecoveryPasswordSecretaryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String?,

    @OneToOne
    @JoinColumn(name = "fk_secretaria_id", referencedColumnName = "id")
    val secretary: SecretaryEntity,

    @Column(name = "codigo_recuperacao", nullable = false, length = 6)
    val token: String,

    @Column(name = "momento", nullable = false)
    val moment: LocalDateTime,
)