package br.com.messore.tech.turtleracing.data.repositories

import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.domain.model.Token
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: TokenDataSource.Local,
    private val remoteDataSource: TokenDataSource.Remote
) : TokenRepository {

    override suspend fun getToken(): Token {
        return localDataSource.getToken() ?: remoteDataSource.getToken().also {
            localDataSource.save(it)
        }
    }
}
