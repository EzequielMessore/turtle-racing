package br.com.messore.tech.turtleracing.data.remote.service

import br.com.messore.tech.turtleracing.data.remote.model.ListTurtleResponse
import br.com.messore.tech.turtleracing.data.remote.model.TurtleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import br.com.messore.tech.turtleracing.data.remote.model.Run as RunRemote

interface TurtleService {

    @GET("getTurtles")
    suspend fun getAll(): ListTurtleResponse

    @GET("play")
    suspend fun play(@Query("id") turtleId: String): RunRemote

    @GET("getTurtle")
    suspend fun getTurtle(@Query("id") turtleId: String): TurtleResponse

    @GET("feed")
    suspend fun recovery(@Query("id") turtleId: String)
}
