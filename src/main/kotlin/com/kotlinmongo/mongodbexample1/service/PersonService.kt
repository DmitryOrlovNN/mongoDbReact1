package com.kotlinmongo.mongodbexample1.service


import com.kotlinmongo.mongodbexample1.dto.PersonDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PersonService {
    fun create(person: PersonDto): Mono<PersonDto>
    fun getAll(): Flux<PersonDto>
    fun getById(id: String): Mono<PersonDto>
    fun update(id: String, person: PersonDto): Mono<PersonDto>
    fun delete(id: String): Mono<Void>
}