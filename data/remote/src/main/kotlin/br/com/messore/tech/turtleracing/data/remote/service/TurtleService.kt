package br.com.messore.tech.turtleracing.data.remote.service

import br.com.messore.tech.turtleracing.data.remote.model.Turtle as TurtleRemote
import br.com.messore.tech.turtleracing.data.remote.model.Run as RunRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface TurtleService {

    @GET("getTurtles")
    suspend fun getTurtles(): List<TurtleRemote>

    @GET("play")
    suspend fun play(@Query("id") turtleId: String): RunRemote
}
