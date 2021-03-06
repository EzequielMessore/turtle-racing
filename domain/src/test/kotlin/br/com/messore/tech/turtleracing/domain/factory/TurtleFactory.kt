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

    fun getTurtle(): Turtle {
        return Turtle(
            id = UUID.randomUUID().toString(),
            energy = 100,
            type = TurtleType.COMMON,
            age = 30,
            run = 2,
            timer = LocalTime.of(10, 20, 30),
            visibleId = "1234"
        )
    }

    fun getTurtleAbleToRun(): Turtle {
        return getTurtle().copy(
            id = "1",
            missingRun = 1
        )
    }
}
