package com.fatecitaquera.carteirinhadigital.controllers

import com.fatecitaquera.carteirinhadigital.dto.CreateStudentDTO
import com.fatecitaquera.carteirinhadigital.dto.ViewStudentDTO
import com.fatecitaquera.carteirinhadigital.entities.StudentEntity
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.services.StudentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/estudante")
class StudentController(
    val service: StudentService,
    val mapper: StudentMapper
) {

    @GetMapping("/encontrar-todos")
    fun findAll(): List<ViewStudentDTO> = mapper.toListDTO(service.findAll())


    @GetMapping("/encontrar-por-ra/{ra}")
    fun findByRa(@RequestParam ra: String): ViewStudentDTO =
        mapper.toDTO(service.findByRa(ra))

    @PostMapping("/criar")
    fun create(@RequestBody student: CreateStudentDTO) {
        service.create(mapper.toEntity(student))
    }
}