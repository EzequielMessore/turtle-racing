package br.com.messore.tech.turtleracing.data.remote.model

import br.com.messore.tech.turtleracing.domain.model.TurtleType
import com.google.gson.annotations.SerializedName

data class TurtleResponse(val turtle: Turtle)
data class ListTurtleResponse(val turtles: List<Turtle>)

data class Turtle(
    val id: String,
    val energy: Long,
    @SerializedName("typeF")
    val type: TurtleType,
    val age: Long,
    val run: Int,
    @SerializedName("timerF")
    val timer: String,
    @SerializedName("runF")
    val missingRun: String,
    val idF: String,
)
