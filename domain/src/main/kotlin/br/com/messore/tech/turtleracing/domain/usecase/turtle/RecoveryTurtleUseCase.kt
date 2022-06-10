package br.com.messore.tech.turtleracing.domain.usecase.turtle

import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import javax.inject.Inject

class RecoveryTurtleUseCase @Inject constructor(
    private val turtleRepository: TurtleRepository
) {
    suspend operator fun invoke(turtleId: String) {
        turtleRepository.recovery(turtleId)
    }
}
