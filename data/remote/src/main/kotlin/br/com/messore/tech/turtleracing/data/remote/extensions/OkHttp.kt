package br.com.messore.tech.turtleracing.data.remote.extensions

import okhttp3.Request

const val AUTHORIZATION = "Authorization"

fun Request.Builder.useToken(token: String): Request.Builder = header(AUTHORIZATION, "Bearer $token")
