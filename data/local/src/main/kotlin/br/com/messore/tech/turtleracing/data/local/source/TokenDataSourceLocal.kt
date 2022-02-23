package br.com.messore.tech.turtleracing.data.local.source

import br.com.messore.tech.turtleracing.data.local.dao.TokenDao
import br.com.messore.tech.turtleracing.data.local.mapper.toDomain
import br.com.messore.tech.turtleracing.data.local.mapper.toEntity
import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.domain.model.Token
import javax.inject.Inject

class TokenDataSourceLocal @Inject constructor(
    private val dao: TokenDao
) : TokenDataSource.Local {

    override suspend fun save(token: Token) {
        dao.save(token.toEntity())
    }

    override suspend fun getToken(): Token? {
        return dao.getToken()?.toDomain()
    }
}
