package br.com.messore.tech.turtleracing.data.remote.extensions

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

const val AUTHORIZATION = "Authorization"

internal fun Request.Builder.useToken(token: String): Request.Builder = header(AUTHORIZATION, "Bearer $token")

internal fun OkHttpClient.Builder.addInterceptors(interceptors: Collection<Interceptor>) = apply {
    interceptors.forEach { addInterceptor(it) }
}
