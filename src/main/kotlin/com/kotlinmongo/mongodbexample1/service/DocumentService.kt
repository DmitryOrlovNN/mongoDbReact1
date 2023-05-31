package com.kotlinmongo.mongodbexample1.service

import com.kotlinmongo.mongodbexample1.dto.DocumentDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface DocumentService {
    fun create(person: DocumentDto): Mono<DocumentDto>
    fun getAll(): Flux<DocumentDto>
    fun getById(id: String): Mono<DocumentDto>
    fun getAllByIds(ids: List<String>): Flux<DocumentDto>
    fun update(id: String, person: DocumentDto): Mono<DocumentDto>
    fun delete(id: String): Mono<Void>
}