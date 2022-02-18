package br.com.messore.tech.turtleracing.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CoroutineDispatcherDefault
