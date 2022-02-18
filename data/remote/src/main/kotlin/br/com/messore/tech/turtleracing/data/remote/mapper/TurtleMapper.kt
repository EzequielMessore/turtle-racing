package br.com.messore.tech.turtleracing.data.remote.mapper

import br.com.messore.tech.turtleracing.data.remote.model.Turtle
import br.com.messore.tech.turtleracing.domain.model.Turtle as TurtleDomain

fun List<Turtle>.toDomain() = map { it.toDomain() }

fun Turtle.toDomain() = TurtleDomain(
    id = id,
    energy = energy,
    type = type,
    age = age,
    run = run,
    timer = timer,
)
