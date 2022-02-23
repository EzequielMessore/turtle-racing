package br.com.messore.tech.turtleracing.data.local.di

import br.com.messore.tech.turtleracing.data.local.source.TokenDataSourceLocal
import br.com.messore.tech.turtleracing.data.local.source.TurtleDataSourceLocal
import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalModule {

    @Binds
    fun providesTokenDataSource(impl: TokenDataSourceLocal): TokenDataSource.Local

    @Binds
    fun providesTurtleDataSource(impl: TurtleDataSourceLocal): TurtleDataSource.Local

}
