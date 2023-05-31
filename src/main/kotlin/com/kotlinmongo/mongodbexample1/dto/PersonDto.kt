package com.kotlinmongo.mongodbexample1.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime
import java.util.*

@Document(collection = "persons")
data class PersonDto(
    @Id
    var id: String? = null,
    val name: String,
    val surname: String,
    val patronymic: String,
    val gender: String,
    val placeOfBirth: String,
    val maritalStatus: String,
    val birthDate: Date,
    @DocumentReference
    val documents: MutableList<DocumentDto> =  mutableListOf(),
    @DocumentReference
    val contacts: MutableList<ContactDto> = mutableListOf(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)