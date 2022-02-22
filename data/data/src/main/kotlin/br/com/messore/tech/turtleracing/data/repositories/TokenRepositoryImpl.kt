package br.com.messore.tech.turtleracing.data.repositories

import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.domain.model.Token
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: TokenDataSource.Local,
    private val remoteDataSource: TokenDataSource.Remote
) : TokenRepository {
    override fun getNewToken(): Token = runBlocking(Dispatchers.IO) {
        remoteDataSource.getToken().also {
            localDataSource.save(it)
        }
    }

    override fun getToken(): Token = runBlocking(Dispatchers.IO) {
        localDataSource.getToken() ?: getNewToken()
    }
}
