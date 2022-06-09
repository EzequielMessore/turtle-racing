package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import br.com.messore.tech.turtleracing.domain.usecase.turtle.GetTurtleUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalTime

@ExperimentalCoroutinesApi
class ScheduleOneRunnerUseCaseTest {

    private val repository = mockk<TurtleRepository>()
    private val getTurtleUseCase = GetTurtleUseCase(repository)
    private val turtleRunnerScheduler = mockk<TurtleRunnerScheduler>()
    private val useCase = ScheduleOneRunnerUseCase(getTurtleUseCase, turtleRunnerScheduler)

    @Test
    fun `invoke schedule run turtle Then schedule one runner`() = runTest {
        coEvery { repository.getTurtle(any()) } returns TurtleFactory.getTurtle()
        every { turtleRunnerScheduler.schedule(any(), any()) } just runs

        val turtleId = "1"

        useCase(turtleId)

        coVerify(exactly = 1) {
            getTurtleUseCase(any())
        }

        verify(exactly = 1) {
            turtleRunnerScheduler.schedule(any(), any())
        }
    }
}
