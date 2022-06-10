package br.com.messore.tech.turtleracing.domain.usecase.runner

import javax.inject.Inject

class ScheduleRecoveryUseCase @Inject constructor(
    private val turtleRunnerScheduler: TurtleRunnerScheduler,
) {
    operator fun invoke() {
        turtleRunnerScheduler.recovery()
    }
}
