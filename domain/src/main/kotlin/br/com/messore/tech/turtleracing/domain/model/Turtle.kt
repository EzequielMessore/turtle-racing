package br.com.messore.tech.turtleracing.domain.model

import java.time.LocalTime

data class Turtle(
    val id: String,
    val energy: Long,
    val type: TurtleType,
    val age: Long,
    val run: Int,
    val timer: LocalTime
)
