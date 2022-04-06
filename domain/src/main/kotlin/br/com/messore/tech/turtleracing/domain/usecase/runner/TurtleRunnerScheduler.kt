package br.com.messore.tech.turtleracing.domain.usecase.runner

import java.time.LocalTime

interface TurtleRunnerScheduler {
    fun schedule(turtleId: String, timer: LocalTime)
}
