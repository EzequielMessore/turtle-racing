package br.com.messore.tech.turtleracing.data.remote.repository

import br.com.messore.tech.turtleracing.data.remote.extensions.getOrThrowDomainError
import br.com.messore.tech.turtleracing.data.remote.mapper.toDomain
import br.com.messore.tech.turtleracing.data.remote.service.AuthService
import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.domain.model.ApiConfig
import br.com.messore.tech.turtleracing.domain.model.Token
import javax.inject.Inject

class TokenDataSourceRemote @Inject constructor(
    private val authService: AuthService,
    private val config: ApiConfig,
) : TokenDataSource.Remote {
    override suspend fun getToken(): Token {
        return config.runCatching {
            authService.login(
                wallet = wallet,
                sign = sign,
                hash = hash
            )
        }.getOrThrowDomainError().toDomain()
    }
}
