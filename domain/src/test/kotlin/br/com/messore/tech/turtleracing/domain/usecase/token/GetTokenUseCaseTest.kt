package br.com.messore.tech.turtleracing.domain.usecase.token

import br.com.messore.tech.turtleracing.domain.factory.TokenFacktory
import br.com.messore.tech.turtleracing.domain.repositories.TokenRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GetTokenUseCaseTest {

    private val repository = mockk<TokenRepository>()
    private val useCase = GetTokenUseCase(repository)

    @Test
    fun `invoke Given GetToken returns status success Then should return a token`() = runTest {
        coEvery { repository.getToken() } returns TokenFacktory.getToken()

        val token = useCase()

        coVerify(exactly = 1) { repository.getToken() }
        assertEquals(expected = "73fabf32-27b4-469b-8f06-f2ef600fa4fc", actual = token)
    }

    @Test
    fun `invoke Given GetToken returns status success Then should return a empty token`() =
        runTest {
            coEvery { repository.getToken() } returns TokenFacktory.getEmptyToken()

            val token = useCase()

            coVerify(exactly = 1) { repository.getToken() }
            assertEquals(expected = "", actual = token)
        }

    @Test
    fun `invoke Given GetToken throws an exception Then should throw the exception`() = runTest {
        coEvery { repository.getToken() } throws Throwable("any exception")

        assertFailsWith<Throwable>("any exception") {
            useCase()
        }

        coVerify(exactly = 1) { repository.getToken() }
    }
}
