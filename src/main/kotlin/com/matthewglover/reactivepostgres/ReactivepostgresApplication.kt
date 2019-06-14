package com.matthewglover.reactivepostgres

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.function.DatabaseClient
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

@SpringBootApplication
class ReactivepostgresApplication(private val databaseClient: DatabaseClient) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(ReactivepostgresApplication::class.java.simpleName)

    override fun run(vararg args: String?) {
        createPerson()
                .toFlux()
                .flatMap {
                    fetchPeople()
                }
                .subscribe { logger.info("selected - $it") }
    }

    private fun createPerson() =
            databaseClient.execute()
                    .sql("INSERT INTO people (name, age) VALUES ('chloe', 8) ON CONFLICT DO NOTHING")
                    .fetch()
                    .rowsUpdated();

    private fun fetchPeople(): Flux<Person> {
        return databaseClient.execute()
                .sql("SELECT person_id, name, age FROM people")
                .`as`(Person::class.java)
                .fetch().all()
    }

}

fun main(args: Array<String>) {
    runApplication<ReactivepostgresApplication>(*args)
}

data class Person(
        val personId: Int,
        val name: String,
        val age: Int
)
