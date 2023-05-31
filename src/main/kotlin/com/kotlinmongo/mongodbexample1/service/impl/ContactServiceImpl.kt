package com.kotlinmongo.mongodbexample1.service.impl

import com.kotlinmongo.mongodbexample1.dto.ContactDto
import com.kotlinmongo.mongodbexample1.repository.ContactRepository
import com.kotlinmongo.mongodbexample1.service.ContactService

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class ContactServiceImpl(private val contactRepository: ContactRepository) : ContactService {

    override fun create(contact: ContactDto): Mono<ContactDto> {
        return contactRepository.save(contact)
    }

    override fun getAll(): Flux<ContactDto> {
        return contactRepository.findAll()
    }

    override fun getById(id: String): Mono<ContactDto> {
        return contactRepository.findById(id)
    }

    override fun getAllByIds(ids: List<String>): Flux<ContactDto> {
        return contactRepository.findAllById(ids)
    }

    override fun update(id: String, contact: ContactDto): Mono<ContactDto> {
        return contactRepository.findById(id)
            .flatMap { existingContact ->
                val updatedContact = existingContact.copy(
                    updatedAt = LocalDateTime.now(),
                    value = contact.value,
                    type = contact.type
                )
                contactRepository.save(updatedContact)
            }
    }

    override fun delete(id: String): Mono<Void> {
        return contactRepository.deleteById(id)
    }
}