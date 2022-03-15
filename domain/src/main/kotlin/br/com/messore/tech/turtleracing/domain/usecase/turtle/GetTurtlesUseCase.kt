package br.com.messore.tech.turtleracing.domain.usecase.turtle

import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import javax.inject.Inject

class GetTurtlesUseCase @Inject constructor(
    private val turtleRepository: TurtleRepository
) {
    suspend operator fun invoke(): List<Turtle> {
        return turtleRepository.getTurtles()
    }
}
