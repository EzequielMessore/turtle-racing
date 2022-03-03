package br.com.messore.tech.turtleracing.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query
import br.com.messore.tech.turtleracing.data.remote.model.Token as TokenRemote

interface AuthService {
    @GET("getToken")
    suspend fun login(
        @Query("coinbase") wallet: String,
        @Query("sign") sign: String,
        @Query("hash") hash: String,
    ): TokenRemote
}
