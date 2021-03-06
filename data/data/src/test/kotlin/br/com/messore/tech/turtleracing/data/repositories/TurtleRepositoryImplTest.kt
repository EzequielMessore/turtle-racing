package br.com.messore.tech.turtleracing.data.repositories

import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class TurtleRepositoryImplTest {

    private val local = mockk<TurtleDataSource.Local>()
    private val remote = mockk<TurtleDataSource.Remote>()
    private val repository = TurtleRepositoryImpl(local, remote)

    @Test
    fun `getAll Given returns success Then should returns turtles and save then`() = runTest {
        coEvery { remote.getAll() } returns listOf()
        coEvery { local.save(any()) } just Runs

        val turtles = repository.getAll()

        coVerify(exactly = 1) {
            remote.getAll()
            local.save(any())
        }

        assertTrue(actual = turtles.isEmpty())
    }

    @Test
    fun `getTurtles Given throws an exception Then should throw the exception`() = runTest {
        coEvery { remote.getAll() } throws Throwable("any exception")

        assertFailsWith<Throwable>("any exception") {
            repository.getAll()
        }

        coVerify(exactly = 1) { remote.getAll() }
        coVerify(exactly = 0) { local.save(any()) }
    }
}
