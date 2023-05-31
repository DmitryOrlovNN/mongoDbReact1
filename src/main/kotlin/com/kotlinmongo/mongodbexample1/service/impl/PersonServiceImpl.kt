package com.kotlinmongo.mongodbexample1.service.impl


import com.kotlinmongo.mongodbexample1.dto.ContactDto
import com.kotlinmongo.mongodbexample1.dto.DocumentDto
import com.kotlinmongo.mongodbexample1.dto.PersonDto
import com.kotlinmongo.mongodbexample1.repository.PersonRepository
import com.kotlinmongo.mongodbexample1.service.PersonService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class PersonServiceImpl(
    private val personRepository: PersonRepository
) : PersonService {

    override fun create(person: PersonDto): Mono<PersonDto> {
        return personRepository.save(person)
    }

    private fun getDocumentsForPerson(personId: String): Flux<DocumentDto> {
        return personRepository.findById(personId)
            .flatMapMany { Flux.fromIterable(it.documents) }
    }

    private fun getContactsForPerson(personId: String): Flux<ContactDto> {
        return personRepository.findById(personId)
            .flatMapMany { Flux.fromIterable(it.contacts) }
    }

    override fun getAll(): Flux<PersonDto> {
        return personRepository.findAll()
            .flatMap { person ->
                Mono.just(person)
                    .zipWith(getDocumentsForPerson(person.id!!).collectList())
                    .zipWith(getContactsForPerson(person.id!!).collectList())
                    .map { tuple ->
                        person.copy(
                            documents = tuple.t1.t2,
                            contacts = tuple.t2
                        )
                    }
            }
    }

    override fun getById(id: String): Mono<PersonDto> {
        return personRepository.findById(id)
    }

    override fun update(id: String, person: PersonDto): Mono<PersonDto> {
        return personRepository.findById(id)
            .flatMap { existingPerson ->
                val updatedPerson = existingPerson.copy(
                    name = person.name,
                    updatedAt = LocalDateTime.now()
                )
                personRepository.save(updatedPerson)
            }
    }

    override fun delete(id: String): Mono<Void> {
        return personRepository.deleteById(id)
    }
}