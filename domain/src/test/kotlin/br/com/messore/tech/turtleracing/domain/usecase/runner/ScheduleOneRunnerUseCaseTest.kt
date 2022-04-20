package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import br.com.messore.tech.turtleracing.domain.usecase.turtle.GetTurtleUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ScheduleOneRunnerUseCaseTest {

    private val repository = mockk<TurtleRepository>()
    private val getTurtleUseCase = GetTurtleUseCase(repository)
    private val turtleRunnerScheduler = mockk<TurtleRunnerScheduler>()
    private val useCase = ScheduleOneRunnerUseCase(getTurtleUseCase, turtleRunnerScheduler)

    @Test
    fun `invoke schedule run turtle Then schedule one runner`() = runTest {
        coEvery { repository.getTurtle("1") } returns TurtleFactory.getTurtle()
        every { turtleRunnerScheduler.schedule(any(), any()) } just runs

        val turtleId = "1"

        coVerify(exactly = 0) {
            useCase(turtleId)
        }
    }
}
