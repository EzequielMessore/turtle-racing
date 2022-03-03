package br.com.messore.tech.turtleracing.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class RunnerWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return Result.success()
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
