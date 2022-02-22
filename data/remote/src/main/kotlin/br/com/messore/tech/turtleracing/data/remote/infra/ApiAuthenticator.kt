package br.com.messore.tech.turtleracing.data.remote.infra

import br.com.messore.tech.turtleracing.data.remote.extensions.useToken
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Response
import okhttp3.Route

class ApiAuthenticator(
    private val tokenRepository: TokenRepository,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response) = runBlocking {
        val token = tokenRepository.getNewToken()

        return@runBlocking response
            .request
            .newBuilder()
            .useToken(token.token)
            .build()
    }
}
