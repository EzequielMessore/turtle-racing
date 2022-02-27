package br.com.messore.tech.turtleracing.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import br.com.messore.tech.turtleracing.domain.usecase.token.PrinterUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.time.Duration
import javax.inject.Inject

@HiltWorker
class RunnerWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    val useCase: PrinterUseCase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val useCase1 = useCase("test")
        Log.e("TAG", "doWork: $useCase1" )
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
