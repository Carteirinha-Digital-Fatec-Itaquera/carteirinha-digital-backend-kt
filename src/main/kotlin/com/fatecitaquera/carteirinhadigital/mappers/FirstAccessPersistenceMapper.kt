package com.fatecitaquera.carteirinhadigital.mappers

import com.fatecitaquera.carteirinhadigital.domains.FirstAccessStudentDomain
import com.fatecitaquera.carteirinhadigital.entities.FirstAccessStudentEntity
import org.springframework.stereotype.Component

@Component
class FirstAccessPersistenceMapper(
    private val studentMapper: StudentMapper
) {

    fun toDomain(entity: FirstAccessStudentEntity): FirstAccessStudentDomain =
        FirstAccessStudentDomain(
            entity.id,
            studentMapper.toDomain(entity.student),
            entity.token,
            entity.moment
        )

    fun toEntity(entity: FirstAccessStudentDomain): FirstAccessStudentEntity =
        FirstAccessStudentEntity(
            entity.id,
            studentMapper.toEntity(entity.student),
            entity.token,
            entity.moment
        )
}