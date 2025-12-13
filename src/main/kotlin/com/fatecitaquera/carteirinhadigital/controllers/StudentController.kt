package com.fatecitaquera.carteirinhadigital.controllers

import com.fatecitaquera.carteirinhadigital.dto.student.StudentDTO
import com.fatecitaquera.carteirinhadigital.dto.student.ViewStudentDTO
import com.fatecitaquera.carteirinhadigital.mappers.StudentMapper
import com.fatecitaquera.carteirinhadigital.services.StudentService
import com.fatecitaquera.carteirinhadigital.services.security.TokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/estudantes")
class StudentController(
    private val service: StudentService,
    private val mapper: StudentMapper,
    private val tokenService: TokenService
) {

    @GetMapping("/encontrar-todos")
    fun findAllByQuery(@RequestParam query: String):  ResponseEntity<List<ViewStudentDTO>> =
        ResponseEntity.ok().body(mapper.toListDTO(service.findAllByQuery(query)))

    @GetMapping("/encontrar-por-ra/{ra}")
    fun findByRa(@PathVariable ra: String): ResponseEntity<ViewStudentDTO> =
        ResponseEntity.ok().body(mapper.toDTO(service.findByRa(ra)))

    @GetMapping("/buscar-carteirinha")
    fun findProfile(request: HttpServletRequest): ResponseEntity<ViewStudentDTO> {
        val ra = tokenService.getIdFromRequest(request)
        return ResponseEntity.ok().body(mapper.toDTO(service.findByRa(ra)))
    }

    @PostMapping("/criar")
    fun create(@Valid @RequestBody student: StudentDTO): ResponseEntity<Void> {
        service.create(mapper.toDomain(student))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/atualizar/{ra}")
    fun update(@PathVariable ra: String, @Valid @RequestBody student: StudentDTO): ResponseEntity<Void> {
        service.update(ra, mapper.toDomain(student))
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/deletar/{ra}")
    fun delete(@PathVariable ra: String): ResponseEntity<Void>  {
        service.delete(ra)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}