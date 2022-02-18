package br.com.messore.tech.turtleracing.workers.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import br.com.messore.tech.turtleracing.workers.RunnerWorker
import javax.inject.Inject

class WorkerFactory @Inject constructor(
    private val runnerFactory: RunnerWorker.Factory,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            RunnerWorker::class.java.name -> runnerFactory.create(appContext, workerParameters)
            else -> null
        }
    }
}
