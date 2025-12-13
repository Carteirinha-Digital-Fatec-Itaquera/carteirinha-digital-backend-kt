package com.fatecitaquera.carteirinhadigital.entities

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_secretaria")
class SecretaryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(name = "nome_completo", nullable = false, length = 50)
    val name: String,

    @Column(unique = true, nullable = false, length = 100)
    val email: String,

    @Column(name = "senha", nullable = false)
    val password: String,

    @Column(name = "papel", nullable = false)
    val role: UserRoleEnum = UserRoleEnum.SECRETARY
)