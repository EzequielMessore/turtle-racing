package br.com.messore.tech.turtleracing.workers.di

import br.com.messore.tech.turtleracing.domain.usecase.runner.TurtleRunnerScheduler
import br.com.messore.tech.turtleracing.workers.TurtleRunnerSchedulerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WorkerModule {

    @Binds
    @Singleton
    fun providesTurtleRunnerScheduler(impl: TurtleRunnerSchedulerImpl): TurtleRunnerScheduler
}
