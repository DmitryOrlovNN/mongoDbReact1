package com.kotlinmongo.mongodbexample1.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.kotlinmongo.mongodbexample1"])
class MongoConfig(
    @Value("\${spring.data.mongodb.database}") private val databaseName: String,
    @Value("\${spring.data.mongodb.uri}") private val uri: String
) : AbstractReactiveMongoConfiguration() {
    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create(uri)
    }

    override fun getDatabaseName(): String {
        return databaseName
    }

    @Bean
    fun transactionManager(reactiveMongoDatabaseFactory: ReactiveMongoDatabaseFactory) = ReactiveMongoTransactionManager(reactiveMongoDatabaseFactory)

    @Bean
    fun transactionalOperator(reactiveTransactionManager: ReactiveTransactionManager) = TransactionalOperator.create(reactiveTransactionManager)

}