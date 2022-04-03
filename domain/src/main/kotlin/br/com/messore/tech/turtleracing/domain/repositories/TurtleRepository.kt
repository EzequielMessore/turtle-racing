package br.com.messore.tech.turtleracing.domain.repositories

import br.com.messore.tech.turtleracing.domain.model.Run
import br.com.messore.tech.turtleracing.domain.model.Turtle

interface TurtleRepository {
    suspend fun getAll(): List<Turtle>
    suspend fun play(turtleId: String): Run
    suspend fun save(turtles: List<Turtle>)
    suspend fun getTurtle(turtleId: String): Turtle
}
