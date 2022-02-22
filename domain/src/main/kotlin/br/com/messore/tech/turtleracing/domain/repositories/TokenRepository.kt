package br.com.messore.tech.turtleracing.domain.repositories

import br.com.messore.tech.turtleracing.domain.model.Token

interface TokenRepository {
    fun getToken(): Token
    fun getNewToken(): Token
}
