package br.com.messore.tech.turtleracing.data.source

import br.com.messore.tech.turtleracing.domain.model.Token

sealed interface TokenDataSource {
    interface Local : TokenDataSource {
        suspend fun save(token: Token)
        suspend fun getToken(): Token?
    }

    interface Remote : TokenDataSource {
        suspend fun getToken(): Token
    }
}
