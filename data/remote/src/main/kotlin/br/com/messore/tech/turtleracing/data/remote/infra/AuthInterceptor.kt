package br.com.messore.tech.turtleracing.data.remote.infra

import br.com.messore.tech.turtleracing.data.remote.extensions.useToken
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(
    private val tokenRepository: TokenRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()

        val token = tokenRepository.getToken()
        requestBuilder.useToken(token.token)

        return chain.proceed(requestBuilder.build())
    }
}
