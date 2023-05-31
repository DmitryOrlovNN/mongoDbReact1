package com.kotlinmongo.mongodbexample1.repository

import com.kotlinmongo.mongodbexample1.dto.DocumentDto
import org.springframework.data.repository.reactive.ReactiveCrudRepository


interface DocumentRepository : ReactiveCrudRepository<DocumentDto, String>