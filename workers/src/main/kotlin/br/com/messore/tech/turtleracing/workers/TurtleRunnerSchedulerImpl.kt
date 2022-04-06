package br.com.messore.tech.turtleracing.workers

import android.content.Context
import br.com.messore.tech.turtleracing.domain.usecase.runner.TurtleRunnerScheduler
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalTime
import javax.inject.Inject

class TurtleRunnerSchedulerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : TurtleRunnerScheduler {

    override fun schedule(turtleId: String, timer: LocalTime) {
        RunnerWorker.start(context, turtleId, timer)
    }
}
