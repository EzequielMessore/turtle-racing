package br.com.messore.tech.turtleracing.data.remote.model

import br.com.messore.tech.turtleracing.domain.model.TurtleType
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class Turtle(
    val id: String,
    val energy: Long,
    @SerializedName("typeF")
    val type: TurtleType,
    val age: Long,
    val run: Int,
    @SerializedName("timerF")
    val timer: LocalTime
)

// turtle:1:1
// turtle:1:2

// turtle:2:1
// turtle:2:2

// turtle:3:1
// turtle:3:2

// turtle:4:1
// turtle:4:2

