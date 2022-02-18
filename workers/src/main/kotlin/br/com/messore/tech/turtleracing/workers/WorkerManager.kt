package br.com.messore.tech.turtleracing.workers

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import br.com.messore.tech.turtleracing.workers.di.WorkerFactory

object WorkerManager {
    fun init(context: Context, factory: WorkerFactory) {
        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(factory)
            .build()
        WorkManager.initialize(context, workManagerConfig)
    }
}
