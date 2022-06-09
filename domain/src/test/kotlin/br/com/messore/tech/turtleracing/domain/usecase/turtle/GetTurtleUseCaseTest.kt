package br.com.messore.tech.turtleracing.domain.usecase.turtle

import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GetTurtleUseCaseTest {
    private val repository = mockk<TurtleRepository>()
    private val useCase = GetTurtleUseCase(repository)
    private val turtleId = "1"

    @Test
    fun `invoke Given getTurtle returns status success Then should return a turtle`() = runTest {
        coEvery { repository.getTurtle(any()) } returns TurtleFactory.getTurtle()

        val turtle = useCase(turtleId)

        coVerify(exactly = 1) { repository.getTurtle(turtleId) }

        assertEquals(expected = 100, actual = turtle.energy)
        assertEquals(expected = TurtleType.COMMON, actual = turtle.type)
        assertEquals(expected = 30, actual = turtle.age)
        assertEquals(expected = 2, actual = turtle.run)
        assertEquals(expected = LocalTime.of(10, 20, 30), actual = turtle.timer)
        assertEquals(expected = "1234", actual = turtle.visibleId)
    }

    @Test
    fun `invoke Given getTurtle throws an exception Then should throw the exception`() = runTest {
        coEvery { repository.getTurtle(any()) } throws Throwable("any exception")

        assertFailsWith<Throwable>("any exception") {
            useCase(turtleId)
        }

        coVerify(exactly = 1) { repository.getTurtle(turtleId) }
    }
}
