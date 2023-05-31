package com.kotlinmongo.mongodbexample1.controller

import com.kotlinmongo.mongodbexample1.dto.ContactDto
import com.kotlinmongo.mongodbexample1.service.ContactService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/contacts")
class ContactController(private val contactService: ContactService) {

    @PostMapping
    fun create(@RequestBody contact: ContactDto): Mono<ContactDto> {
        return contactService.create(contact)
    }

    @GetMapping
    fun getAll(): Flux<ContactDto> {
        return contactService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Mono<ResponseEntity<ContactDto>> {
        return contactService.getById(id)
            .map { contact -> ResponseEntity.ok(contact) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody contact: ContactDto): Mono<ResponseEntity<ContactDto>> {
        return contactService.update(id, contact)
            .map { updatedContact -> ResponseEntity.ok(updatedContact) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Mono<ResponseEntity<Void>> {
        return contactService.delete(id)
            .thenReturn(ResponseEntity.noContent().build())
    }
}