package br.com.messore.tech.turtleracing.domain.factory

import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import java.time.LocalTime
import java.util.UUID

object TurtleFactory {

    fun getTurtles(quantity: Int) =
        (1..quantity).map {
            getTurtle()
        }

    private fun getTurtle(): Turtle {
        return Turtle(
            id = UUID.randomUUID().toString(),
            energy = 100,
            type = TurtleType.COMMON,
            age = 30,
            run = 2,
            timer = LocalTime.of(10, 20, 30),
        )
    }
}