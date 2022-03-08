package br.com.messore.tech.turtleracing.domain.usecase.turtle

import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import javax.inject.Inject

class SaveTurtleUseCase @Inject constructor(
    private val repository: TurtleRepository
) {
    suspend operator fun invoke(turtles: List<Turtle>) {
        repository.save(turtles)
    }
}
