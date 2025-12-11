package com.fatecitaquera.carteirinhadigital.mappers

import com.fatecitaquera.carteirinhadigital.entities.RecoveryPasswordSecretaryEntity
import com.fatecitaquera.carteirinhadigital.entities.RecoveryPasswordStudentEntity
import com.fatecitaquera.carteirinhadigital.domains.RecoveryPasswordSecretaryDomain
import com.fatecitaquera.carteirinhadigital.domains.RecoveryPasswordStudentDomain
import org.springframework.stereotype.Component

@Component
class RecoveryPasswordPersistenceMapper(
    private val studentMapper: StudentMapper,
    private val secretaryMapper: SecretaryMapper
) {

    fun toDomain(entity: RecoveryPasswordStudentEntity): RecoveryPasswordStudentDomain =
        RecoveryPasswordStudentDomain(
            entity.id,
            studentMapper.toDomain(entity.student),
            entity.token,
            entity.moment
        )

    fun toEntity(entity: RecoveryPasswordStudentDomain): RecoveryPasswordStudentEntity =
        RecoveryPasswordStudentEntity(
            entity.id,
            studentMapper.toEntity(entity.student),
            entity.token,
            entity.moment
        )

    fun toDomain(entity: RecoveryPasswordSecretaryEntity): RecoveryPasswordSecretaryDomain =
        RecoveryPasswordSecretaryDomain(
            entity.id,
            secretaryMapper.toDomain(entity.secretary),
            entity.token,
            entity.moment
        )

    fun toEntity(entity: RecoveryPasswordSecretaryDomain): RecoveryPasswordSecretaryEntity =
        RecoveryPasswordSecretaryEntity(
            entity.id,
            secretaryMapper.toEntity(entity.secretary),
            entity.token,
            entity.moment
        )
}