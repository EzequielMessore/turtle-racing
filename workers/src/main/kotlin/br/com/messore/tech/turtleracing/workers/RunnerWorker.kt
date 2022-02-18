package br.com.messore.tech.turtleracing.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.time.Duration

class RunnerWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val build = Data.Builder().putString("pedro", "lindo").build()
        return Result.success(build)
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): RunnerWorker
    }

    companion object {

        fun start(context: Context) {
            val uploadWorkRequest: WorkRequest =
                OneTimeWorkRequestBuilder<RunnerWorker>()
                    .setInitialDelay(Duration.ofSeconds(30))
                    .build()

            WorkManager
                .getInstance(context)
                .enqueue(uploadWorkRequest)
        }
    }
}
