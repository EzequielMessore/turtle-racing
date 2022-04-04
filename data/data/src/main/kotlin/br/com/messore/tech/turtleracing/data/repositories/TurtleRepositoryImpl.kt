package br.com.messore.tech.turtleracing.data.repositories

import br.com.messore.tech.turtleracing.data.source.TurtleDataSource
import br.com.messore.tech.turtleracing.domain.model.Run
import br.com.messore.tech.turtleracing.domain.model.Turtle
import br.com.messore.tech.turtleracing.domain.repositories.TurtleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TurtleRepositoryImpl @Inject constructor(
    private val localDataSource: TurtleDataSource.Local,
    private val remoteDataSource: TurtleDataSource.Remote,
) : TurtleRepository {

    override suspend fun getTurtle(turtleId: String): Turtle {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Turtle> {
        return remoteDataSource.getTurtles().also {
            localDataSource.save(it)
        }
    }

    override suspend fun play(turtleId: String): Run {
        TODO("Not yet implemented")
    }

    override suspend fun save(turtles: List<Turtle>) = withContext(Dispatchers.IO) {
        localDataSource.save(turtles)
    }
}
