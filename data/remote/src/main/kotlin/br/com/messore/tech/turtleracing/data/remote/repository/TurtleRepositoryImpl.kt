package br.com.messore.tech.turtleracing.data.remote.repository

import br.com.messore.tech.turtleracing.data.remote.UnexpectedException
import br.com.messore.tech.turtleracing.data.remote.extensions.getOrThrowDomainError
import br.com.messore.tech.turtleracing.data.remote.mapper.toDomain
import br.com.messore.tech.turtleracing.data.remote.service.TurtleService
import br.com.messore.tech.turtleracing.domain.model.Run
import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TurtleRepositoryImpl @Inject constructor(
    private val service: TurtleService,
) : TurtleRepository {

    override suspend fun getTurtles(): List<Turtle> {
        return runCatching { service.getTurtles() }
            .getOrThrowDomainError()
            .toDomain()
    }

    override suspend fun play(turtleId: String): Run {
        return runCatching {
            val run = service.play(turtleId)

            if (run.error.isNullOrBlank()) run
            else throw UnexpectedException(run.error)
        }.getOrThrowDomainError().toDomain()
    }

    override suspend fun save(turtles: List<Turtle>) = withContext(Dispatchers.IO) {
        // @TODO implement
    }
}
