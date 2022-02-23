package br.com.messore.tech.turtleracing.data.source

import br.com.messore.tech.turtleracing.domain.model.Turtle

sealed interface TurtleDataSource {
    interface Local : TurtleDataSource {
        suspend fun save(turtles: List<Turtle>)
        suspend fun getAll(): List<Turtle>
    }
}