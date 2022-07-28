package br.com.messore.tech.turtleracing.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.messore.tech.core.notification.createBigTextNotification
import br.com.messore.tech.core.notification.createNotification
import br.com.messore.tech.core.notification.showNotification
import br.com.messore.tech.turtleracing.domain.extensions.format
import br.com.messore.tech.turtleracing.domain.extensions.getDiffInSeconds
import br.com.messore.tech.turtleracing.domain.extensions.toDuration
import br.com.messore.tech.turtleracing.domain.extensions.toMillis
import br.com.messore.tech.turtleracing.domain.usecase.turtle.ListTurtlesUseCase
import br.com.messore.tech.turtleracing.domain.usecase.turtle.RecoveryTurtleUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@HiltWorker
internal class RecoveryWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val listTurtlesUseCase: ListTurtlesUseCase,
    private val recoveryTurtleUseCase: RecoveryTurtleUseCase,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val turtleIds = listTurtlesUseCase()
            .filter { it.hasEnergy.not() }
            .map { it.id }

        turtleIds.forEach { turtleId ->
            makeRecovery(turtleId)
        }

        return Result.success()
    }

    private suspend fun makeRecovery(turtleId: String) {
        runCatching {
            recoveryTurtleUseCase(turtleId)
        }.onSuccess {
            sendSuccessNotification(turtleId)
        }.onFailure {
            sendFailedNotification(turtleId)
        }
    }

    private fun sendSuccessNotification(turtleId: String) = context.run {
        createNotification(
            "",
            "Your turtle: $turtleId was recovered"
        ).also { notification ->
            showNotification(turtleId.hashCode(), notification)
        }
    }

    private fun sendFailedNotification(turtleId: String) = context.run {
        createBigTextNotification(
            "Your turtle: $turtleId can't be recovered",
            "Some error occurred"
        ).also { notification ->
            showNotification(turtleId.hashCode(), notification)
        }
    }

    companion object {

        fun start(context: Context) {
            val diffInSeconds = LocalDateTime.now().getDiffInSeconds()
            val duration = Duration.ofSeconds(diffInSeconds)

            val request = createRequest(LocalDateTime.now().plusSeconds(diffInSeconds), duration)

            WorkManager
                .getInstance(context)
                .enqueueUniqueWork("RecoveryWorker", ExistingWorkPolicy.REPLACE, request)
        }

        private fun createRequest(date: LocalDateTime, initial: Duration) =
            OneTimeWorkRequestBuilder<RecoveryWorker>()
                .addTag(date.format())
                .setInitialDelay(initial)
                .build()
    }
}
