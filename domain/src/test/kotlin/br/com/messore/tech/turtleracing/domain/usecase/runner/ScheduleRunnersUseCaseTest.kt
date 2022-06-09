package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import br.com.messore.tech.turtleracing.domain.usecase.turtle.ListTurtlesUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalTime
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ScheduleRunnersUseCaseTest {

    private val repository = mockk<TurtleRepository>()
    private val listTurtlesUseCase = ListTurtlesUseCase(repository)
    private val turtleRunnerScheduler = mockk<TurtleRunnerScheduler>()
    private val useCase = ScheduleRunnersUseCase(listTurtlesUseCase, turtleRunnerScheduler)

    @Test
    fun `invoke schedule runners for list turtle Then schedule runners`() = runTest {
        coEvery { listTurtlesUseCase() } returns TurtleFactory.getTurtles(1)
        every { turtleRunnerScheduler.schedule(any(), any()) } just runs

        useCase()

        verify(exactly = 1) {
            turtleRunnerScheduler.schedule(any(), any())
        }
    }

    @Test
    fun `invoke schedule runners for list turtle with 3 turtles Then 3 schedule runners`() =
        runTest {
            coEvery { listTurtlesUseCase() } returns TurtleFactory.getTurtles(3)
            every { turtleRunnerScheduler.schedule(any(), any()) } just runs

            useCase()

            verify(exactly = 3) {
                turtleRunnerScheduler.schedule(any(), any())
            }
        }

    @Test
    fun `invoke schedule runners for list turtle Then schedule with one minute add on timer`() =
        runTest {
            val timer = LocalTime.of(0, 30, 0)
            val turtle = TurtleFactory.getTurtle()
                .copy(id = "1", timer = timer)

            coEvery { listTurtlesUseCase() } returns listOf(turtle)
            every { turtleRunnerScheduler.schedule(any(), any()) } just runs

            useCase()

            verify(exactly = 1) {
                turtleRunnerScheduler.schedule(
                    turtleId = withArg { assertEquals(expected = "1", it) },
                    timer = withArg { assertEquals(expected = LocalTime.of(0, 31, 0), it) }
                )
            }
        }
}
