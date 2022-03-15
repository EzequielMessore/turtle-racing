package br.com.messore.tech.turtleracing.data.remote.mapper

import br.com.messore.tech.turtleracing.data.remote.model.Turtle
import java.time.LocalTime
import br.com.messore.tech.turtleracing.domain.model.Turtle as TurtleDomain

internal fun List<Turtle>.toDomain() = map { it.toDomain() }

private fun Turtle.toDomain() = TurtleDomain(
    id = id,
    energy = energy,
    type = type,
    age = age,
    run = run,
    timer = LocalTime.parse(timer),
)
