package br.com.messore.tech.turtleracing.domain.usecase.turtle

import org.junit.Test

class SaveTurtleUseCaseTest {

    lateinit var useCase: SaveTurtleUseCase

    @Test
    fun `Should Save Turtle`() {

        useCase.execute()

        useCase("asdasd")
    }
}
