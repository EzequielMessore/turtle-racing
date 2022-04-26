package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import br.com.messore.tech.turtleracing.domain.usecase.turtle.ListTurtlesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ScheduleRunnersUseCaseTest {

    private val repository = mockk<TurtleRepository>()
    private val listTurtlesUseCase = ListTurtlesUseCase(repository)
    private val turtleRunnerScheduler = mockk<TurtleRunnerScheduler>()
    private val useCase = ScheduleRunnersUseCase(listTurtlesUseCase, turtleRunnerScheduler)

    @Test
    fun `invoke schedule runners for list turtle Then schedule runners`() = runTest {
        coEvery { repository.getAll() } returns TurtleFactory.getTurtles(1)
        every { turtleRunnerScheduler.schedule(any(), any()) } just runs

        coVerify(exactly = 0) {
            useCase()
        }
    }
}
