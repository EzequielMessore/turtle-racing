package br.com.messore.tech.turtleracing.data.di

import br.com.messore.tech.turtleracing.data.repositories.TokenRepositoryImpl
import br.com.messore.tech.turtleracing.data.repositories.TurtleRepositoryImpl
import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesTokenRepository(
        localDataSource: TokenDataSource.Local,
        remoteDataSource: TokenDataSource.Remote,
    ): TokenRepository {
        return TokenRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun providesTurtleRepository(
        localDataSource: TurtleDataSource.Local
    ): TurtleRepository {
        return TurtleRepositoryImpl(localDataSource)
    }
}
