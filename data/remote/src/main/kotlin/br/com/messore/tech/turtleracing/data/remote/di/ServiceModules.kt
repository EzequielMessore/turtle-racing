package br.com.messore.tech.turtleracing.data.remote.di

import br.com.messore.tech.turtleracing.data.remote.service.AuthService
import br.com.messore.tech.turtleracing.data.remote.service.TurtleService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ServiceModules {
    @Provides
    fun provideAuthService(retrofit: Retrofit) = retrofit.create<AuthService>()

    @Provides
    fun provideTurtleService(@Authenticated retrofit: Retrofit) = retrofit.create<TurtleService>()
}
