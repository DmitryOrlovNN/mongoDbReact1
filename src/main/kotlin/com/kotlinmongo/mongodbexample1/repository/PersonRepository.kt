package com.kotlinmongo.mongodbexample1.repository

import com.kotlinmongo.mongodbexample1.dto.PersonDto
import org.springframework.data.repository.reactive.ReactiveCrudRepository


interface PersonRepository : ReactiveCrudRepository<PersonDto, String>