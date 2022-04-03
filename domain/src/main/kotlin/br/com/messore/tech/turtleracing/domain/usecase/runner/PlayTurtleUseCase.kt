package br.com.messore.tech.turtleracing.domain.usecase.runner

import br.com.messore.tech.turtleracing.domain.exceptions.CantRunException
import br.com.messore.tech.turtleracing.domain.model.Run
import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import javax.inject.Inject

class PlayTurtleUseCase @Inject constructor(
    private val turtleRepository: TurtleRepository
) {

    suspend operator fun invoke(turtle: Turtle): Run {
        if (!turtle.canRun) throw CantRunException(turtle.id)

        return turtleRepository.play(turtle.id).also {
            turtle.missingRun--
        }
    }
}
