package br.com.messore.tech.turtleracing.data.remote.repository

import br.com.messore.tech.turtleracing.data.remote.UnexpectedException
import br.com.messore.tech.turtleracing.data.remote.extensions.getOrThrowDomainError
import br.com.messore.tech.turtleracing.data.remote.mapper.toDomain
import br.com.messore.tech.turtleracing.data.remote.service.TurtleService
import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import br.com.messore.tech.turtleracing.domain.model.Run
import br.com.messore.tech.turtleracing.domain.model.Turtle
import javax.inject.Inject

class TurtleDataSourceRemote @Inject constructor(
    private val service: TurtleService,
) : TurtleDataSource.Remote {

    override suspend fun getTurtle(turtleId: String): Turtle {
        return runCatching { service.getTurtle(turtleId).turtle }
            .getOrThrowDomainError()
            .toDomain()
    }

    override suspend fun getAll(): List<Turtle> {
        return runCatching { service.getAll().turtles }
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
}
