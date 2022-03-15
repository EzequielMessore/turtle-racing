package br.com.messore.tech.turtleracing.data.remote.service

import br.com.messore.tech.turtleracing.data.remote.model.TurtleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import br.com.messore.tech.turtleracing.data.remote.model.Run as RunRemote

interface TurtleService {

    @GET("getTurtles")
    suspend fun getTurtles(): TurtleResponse

    @GET("play")
    suspend fun play(@Query("id") turtleId: String): RunRemote
}
