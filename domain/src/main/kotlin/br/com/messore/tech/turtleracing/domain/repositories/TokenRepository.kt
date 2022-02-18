package br.com.messore.tech.turtleracing.domain.repositories

import br.com.messore.tech.turtleracing.domain.model.Token

interface TokenRepository {
    suspend fun getToken(): Token?
}
