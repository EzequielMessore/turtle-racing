package br.com.messore.tech.turtleracing.data.di

import br.com.messore.tech.turtleracing.data.repositories.TokenRepositoryImpl
import br.com.messore.tech.turtleracing.data.repositories.TurtleRepositoryImpl
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun providesTokenRepository(impl: TokenRepositoryImpl): TokenRepository

    @Binds
    fun providesTurtleRepository(impl: TurtleRepositoryImpl): TurtleRepository
}
