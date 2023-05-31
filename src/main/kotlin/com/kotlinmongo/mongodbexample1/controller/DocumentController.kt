package com.kotlinmongo.mongodbexample1.controller
import com.kotlinmongo.mongodbexample1.dto.DocumentDto
import com.kotlinmongo.mongodbexample1.service.DocumentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/documents")
class DocumentController(private val documentService: DocumentService) {

    @PostMapping
    fun create(@RequestBody document: DocumentDto): Mono<DocumentDto> {
        return documentService.create(document)
    }

    @GetMapping
    fun getAll(): Flux<DocumentDto> {
        return documentService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Mono<ResponseEntity<DocumentDto>> {
        return documentService.getById(id)
            .map { document -> ResponseEntity.ok(document) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody document: DocumentDto): Mono<ResponseEntity<DocumentDto>> {
        return documentService.update(id, document)
            .map { updatedDocument -> ResponseEntity.ok(updatedDocument) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Mono<ResponseEntity<Void>> {
        return documentService.delete(id)
            .thenReturn(ResponseEntity.noContent().build())
    }
}