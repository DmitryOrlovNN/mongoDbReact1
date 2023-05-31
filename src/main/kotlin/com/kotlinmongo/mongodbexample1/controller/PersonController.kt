package com.kotlinmongo.mongodbexample1.controller

import com.kotlinmongo.mongodbexample1.dto.PersonDto
import com.kotlinmongo.mongodbexample1.service.ContactService
import com.kotlinmongo.mongodbexample1.service.DocumentService
import com.kotlinmongo.mongodbexample1.service.PersonService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/persons")
class PersonController(private val personService: PersonService,
                       private val documentService: DocumentService,
                       private val contactService: ContactService
) {

    @PostMapping
    fun create(@RequestBody person: PersonDto): Mono<PersonDto> {
        return personService.create(person)
    }

    @GetMapping
    fun getAll(): Flux<PersonDto> {
        return personService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Mono<PersonDto> {
        return personService.getById(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody person: PersonDto): Mono<PersonDto> {
        return personService.update(id, person)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Mono<Void> {
        return personService.delete(id)
    }
}