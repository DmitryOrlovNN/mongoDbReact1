package com.kotlinmongo.mongodbexample1.repository

import com.kotlinmongo.mongodbexample1.dto.ContactDto
import org.springframework.data.repository.reactive.ReactiveCrudRepository


interface ContactRepository : ReactiveCrudRepository<ContactDto, String>