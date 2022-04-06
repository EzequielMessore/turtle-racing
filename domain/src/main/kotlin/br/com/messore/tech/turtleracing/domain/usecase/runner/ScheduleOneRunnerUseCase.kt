package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.usecase.turtle.GetTurtleUseCase
import javax.inject.Inject

class ScheduleOneRunnerUseCase @Inject constructor(
    private val getTurtleUseCase: GetTurtleUseCase,
    private val turtleRunnerScheduler: TurtleRunnerScheduler,
) {
    suspend operator fun invoke(turtleId: String) {
        val turtle = getTurtleUseCase(turtleId)
        turtleRunnerScheduler.schedule(turtle.id, turtle.timer.plusMinutes(1))
    }
}
