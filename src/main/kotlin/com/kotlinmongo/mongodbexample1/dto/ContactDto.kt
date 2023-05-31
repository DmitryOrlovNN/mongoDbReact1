package com.kotlinmongo.mongodbexample1.dto

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document(collection = "contacts")
data class ContactDto(
    @Id
    var id: String? = null,
    val type: String, // Тип контакта (например, email, телефон и т.д.)
    val value: String, // Значение контакта (например, номер телефона или адрес электронной почты)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)