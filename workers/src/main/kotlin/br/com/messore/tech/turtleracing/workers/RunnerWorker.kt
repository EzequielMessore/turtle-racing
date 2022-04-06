package br.com.messore.tech.turtleracing.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.messore.tech.turtleracing.domain.extensions.format
import br.com.messore.tech.turtleracing.domain.extensions.plusDuration
import br.com.messore.tech.turtleracing.domain.extensions.toDuration
import br.com.messore.tech.turtleracing.domain.usecase.runner.PlayTurtleUseCase
import br.com.messore.tech.turtleracing.domain.usecase.runner.ScheduleOneRunnerUseCase
import br.com.messore.tech.turtleracing.domain.usecase.turtle.GetTurtleUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

@HiltWorker
internal class RunnerWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getTurtleUseCase: GetTurtleUseCase,
    private val playTurtleUseCase: PlayTurtleUseCase,
    private val scheduleOneRunnerUseCase: ScheduleOneRunnerUseCase,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val turtleId = inputData.getString(TURTLE_ID)
            ?: throw IllegalArgumentException("turtle id is required")

        val turtle = getTurtleUseCase(turtleId)

        while (turtle.canRun) {
            playTurtleUseCase(turtle)
        }

        scheduleOneRunnerUseCase(turtleId)
        return Result.success()
    }

    companion object {

        private const val TURTLE_ID = "turtleId"
        private const val TAG_WORKERS = "workers"

        fun start(context: Context, turtleId: String, timer: LocalTime) {
            val duration = timer.toDuration()
            val date = LocalDateTime.now().plusDuration(duration)

            val inputData = workDataOf(TURTLE_ID to turtleId)
            val request = createRequest(date, inputData, duration)

            WorkManager
                .getInstance(context)
                .enqueueUniqueWork(turtleId, ExistingWorkPolicy.REPLACE, request)
        }

        private fun createRequest(date: LocalDateTime, data: Data, initial: Duration) =
            OneTimeWorkRequestBuilder<RunnerWorker>()
                .setInputData(data)
                .addTag(TAG_WORKERS)
                .addTag(date.format())
                .setInitialDelay(initial)
                .build()
    }
}
