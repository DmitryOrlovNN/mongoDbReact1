package com.kotlinmongo.mongodbexample1.service


import com.kotlinmongo.mongodbexample1.dto.ContactDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ContactService {
    fun create(person: ContactDto): Mono<ContactDto>
    fun getAll(): Flux<ContactDto>
    fun getAllByIds(ids: List<String>): Flux<ContactDto>
    fun getById(id: String): Mono<ContactDto>
    fun update(id: String, person: ContactDto): Mono<ContactDto>
    fun delete(id: String): Mono<Void>
}