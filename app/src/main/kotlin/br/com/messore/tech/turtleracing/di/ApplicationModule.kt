package br.com.messore.tech.turtleracing.di

import br.com.messore.tech.turtleracing.data.di.DataModule
import br.com.messore.tech.turtleracing.data.local.di.DatabaseModule
import br.com.messore.tech.turtleracing.data.local.di.LocalModule
import br.com.messore.tech.turtleracing.data.remote.di.NetworkModule
import br.com.messore.tech.turtleracing.data.remote.di.RemoteModule
import br.com.messore.tech.turtleracing.data.remote.di.ServiceModules
import br.com.messore.tech.turtleracing.domain.model.ApiConfig
import br.com.messore.tech.turtleracing.workers.di.WorkerModule
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(
    includes = [
        DataModule::class,
        LocalModule::class,
        WorkerModule::class,
        RemoteModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ServiceModules::class,
    ]
)
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Reusable
    fun provideApiConfig(config: WalletConfig): ApiConfig = config

    @Provides
    @CoroutineDispatcherDefault
    fun provideDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
