package br.com.messore.tech.turtleracing.data.local.source

import br.com.messore.tech.turtleracing.data.local.dao.TurtleDao
import br.com.messore.tech.turtleracing.data.local.mapper.toDomain
import br.com.messore.tech.turtleracing.data.local.mapper.toEntity
import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import br.com.messore.tech.turtleracing.domain.model.Turtle
import javax.inject.Inject

class TurtleDataSourceLocal @Inject constructor(
    private val dao: TurtleDao
) : TurtleDataSource.Local {

    override suspend fun save(turtles: List<Turtle>) {
        val turtleVarargs = turtles.map {
            it.toEntity()
        }.toTypedArray()

        dao.save(*turtleVarargs)
    }

    override suspend fun getAll(): List<Turtle> = dao.getAll().map {
        it.toDomain()
    }
}
