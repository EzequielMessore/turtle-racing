package br.com.messore.tech.turtleracing.domain.model

import java.time.LocalTime

data class Turtle(
    val id: String,
    val visibleId: String,
    val energy: Long,
    val type: TurtleType,
    val age: Long,
    val run: Int,
    val timer: LocalTime,
    var missingRun: Int = 0,
    val image: String? = null,
    val expirationTime: Long = 0,
) {
    val canRun get() = missingRun > 0 && energy > 0
    val hasEnergy get() = energy > 0
}
