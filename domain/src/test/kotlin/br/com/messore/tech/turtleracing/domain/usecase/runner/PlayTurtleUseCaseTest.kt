package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.exceptions.CantRunException
import br.com.messore.tech.turtleracing.domain.factory.RunFactory
import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class PlayTurtleUseCaseTest {

    private val repository = mockk<TurtleRepository>()
    private val useCase = PlayTurtleUseCase(repository)

    @Test
    fun `invoke playTurtle returns status success Then should return a run`() = runTest {
        coEvery { repository.play(any()) } returns RunFactory.getRun()

        val run = useCase(TurtleFactory.getTurtleAbleToRun())

        coVerify(exactly = 1) { repository.play("1") }

        assertEquals(expected = 1, actual = run.position)
        assertEquals(expected = "1.50", actual = run.profit)
    }

    @Test
    fun `invoke playTurtle returns status error Then should return CantRunException`() = runTest {
        coEvery { repository.play(any()) } returns RunFactory.getRun()

        assertFailsWith<CantRunException> {
            useCase(TurtleFactory.getTurtle())
        }
    }

    @Test
    fun `invoke playTurtle returns status error Then should throw the exception`() = runTest {
        coEvery { repository.play(any()) } throws Throwable("any exception")

        assertFailsWith<Throwable>("any exception") {
            useCase(TurtleFactory.getTurtle())
        }
    }
}
