package br.com.messore.tech.turtleracing.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity(tableName = "turtle")
data class TurtleEntity(
    @PrimaryKey
    val id: String,
    val energy: Long,
    val type: String,
    val age: Long,
    val run: Int,
    val timer: LocalTime
)