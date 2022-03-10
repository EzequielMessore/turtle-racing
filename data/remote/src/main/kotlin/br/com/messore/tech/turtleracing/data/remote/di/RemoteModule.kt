package br.com.messore.tech.turtleracing.data.remote.di

import br.com.messore.tech.turtleracing.data.remote.repository.TokenDataSourceRemote
import br.com.messore.tech.turtleracing.data.remote.repository.TurtleDataSourceRemote
import br.com.messore.tech.turtleracing.data.source.TokenDataSource
import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import dagger.Binds
import dagger.Module

@Module
interface RemoteModule {

    @Binds
    fun providesAuthRepository(impl: TokenDataSourceRemote): TokenDataSource.Remote

    @Binds
    fun providesTurtleRepository(impl: TurtleDataSourceRemote): TurtleDataSource.Remote
}
