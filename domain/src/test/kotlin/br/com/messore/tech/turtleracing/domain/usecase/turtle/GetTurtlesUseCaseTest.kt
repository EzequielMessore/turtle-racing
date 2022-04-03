package br.com.messore.tech.turtleracing.domain.usecase.turtle

import br.com.messore.tech.turtleracing.domain.factory.TurtleFactory
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetTurtlesUseCaseTest {

    private val repository = mockk<TurtleRepository>()
    private val useCase = ListTurtlesUseCase(repository)

    @Test
    fun `invoke Given getTurtles returns status success Then should return turtles`() = runTest {
        coEvery { repository.getAll() } returns TurtleFactory.getTurtles(3)

        val turtles = useCase()

        coVerify(exactly = 1) { repository.getAll() }

        assertTrue(turtles.isNotEmpty())
        assertEquals(expected = 3, actual = turtles.size)
    }

    @Test
    fun `invoke Given getTurtles throws an exception Then should throw the exception`() = runTest {
        coEvery { repository.getAll() } throws Throwable("any exception")

        assertFailsWith<Throwable>("any exception") {
            useCase()
        }

        coVerify(exactly = 1) { repository.getAll() }
    }
}
