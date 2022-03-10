package br.com.messore.tech.turtleracing.data.remote.di

import br.com.messore.tech.turtleracing.data.remote.extensions.addInterceptors
import br.com.messore.tech.turtleracing.data.remote.infra.ApiAuthenticator
import br.com.messore.tech.turtleracing.data.remote.infra.AuthInterceptor
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val url = "https://play.turtleracing.io/home/"

    @Provides
    @Singleton
    fun providesOkHttpBuilder(
        interceptors: Set<@JvmSuppressWildcards Interceptor>,
    ) = OkHttpClient.Builder().addInterceptors(interceptors)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)

    @Provides
    @Singleton
    fun provideRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient.Builder,
    ): Retrofit = builder
        .client(okHttpClient.build())
        .build()

    @Provides
    @Singleton
    @Authenticated
    fun provideRetrofitAuthenticated(
        builder: Retrofit.Builder,
        authenticator: Authenticator,
        okHttpClient: OkHttpClient.Builder,
        @Authenticated interceptors: Set<@JvmSuppressWildcards Interceptor>,
    ): Retrofit = builder
        .client(
            okHttpClient.authenticator(authenticator)
                .addInterceptors(interceptors).build()
        )
        .build()

    @Provides
    @Singleton
    fun provideApiAuthenticator(tokenRepository: TokenRepository): Authenticator {
        return ApiAuthenticator(tokenRepository)
    }

    @IntoSet
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @IntoSet
    @Provides
    @Singleton
    @Authenticated
    fun provideApiAuthInterceptor(tokenRepository: TokenRepository): Interceptor {
        return AuthInterceptor(tokenRepository)
    }
}
