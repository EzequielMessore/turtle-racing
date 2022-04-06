package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.usecase.turtle.ListTurtlesUseCase
import javax.inject.Inject

class ScheduleRunnersUseCase @Inject constructor(
    private val listTurtleUseCase: ListTurtlesUseCase,
    private val turtleRunnerScheduler: TurtleRunnerScheduler,
) {
    suspend operator fun invoke() {
        listTurtleUseCase().forEach { turtle ->
            turtleRunnerScheduler.schedule(turtle.id, turtle.timer.plusMinutes(1))
        }
    }
}
