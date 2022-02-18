package br.com.messore.tech.turtleracing

import android.app.Application
import br.com.messore.tech.turtleracing.workers.WorkerManager
import br.com.messore.tech.turtleracing.workers.di.WorkerFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
        WorkerManager.init(this, workerFactory)
    }

}
