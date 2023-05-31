package com.kotlinmongo.mongodbexample1.dto


import org.springframework.data.annotation.Id
import org.springframework.data.annotation.ReadOnlyProperty
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document(collection = "documents")
data class DocumentDto(
    @Id
    var id: String? = null,
    val series: String,
    val number: String,
    val name: String?,
    val surname: String?,
    val issued: LocalDateTime?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)