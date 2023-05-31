package com.kotlinmongo.mongodbexample1.service.impl


import com.kotlinmongo.mongodbexample1.dto.DocumentDto
import com.kotlinmongo.mongodbexample1.repository.DocumentRepository
import com.kotlinmongo.mongodbexample1.service.DocumentService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class DocumentServiceImpl(private val documentRepository: DocumentRepository) : DocumentService {

    override fun create(document: DocumentDto): Mono<DocumentDto> {
        return documentRepository.save(document)
    }

    override fun getAll(): Flux<DocumentDto> {
        return documentRepository.findAll()
    }

    override fun getById(id: String): Mono<DocumentDto> {
        return documentRepository.findById(id)
    }

    override fun getAllByIds(ids: List<String>): Flux<DocumentDto> {
        return documentRepository.findAllById(ids)
    }

    override fun update(id: String, document: DocumentDto): Mono<DocumentDto> {
        return documentRepository.findById(id)
            .flatMap { existingDocument ->
                val updatedDocument = existingDocument.copy(
                    series = document.series,
                    number = document.number,
                    name = document.name,
                    surname = document.surname,
                    issued = document.issued,
                    updatedAt = LocalDateTime.now(),
                    createdAt = existingDocument.createdAt,
                )
                documentRepository.save(updatedDocument)
            }
    }

    override fun delete(id: String): Mono<Void> {
        return documentRepository.deleteById(id)
    }
}