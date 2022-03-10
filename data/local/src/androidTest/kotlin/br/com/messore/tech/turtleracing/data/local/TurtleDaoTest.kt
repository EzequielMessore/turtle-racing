package br.com.messore.tech.turtleracing.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.messore.tech.turtleracing.data.local.database.AppDatabase
import br.com.messore.tech.turtleracing.data.local.model.TurtleEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
class TurtleDaoTest {

    private lateinit var database: AppDatabase
    private val dao by lazy { database.turtleDao() }

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun shouldBeSaveAllTurtles(): Unit = runBlocking {
        val turtles = createTurtles(3).toTypedArray()

        dao.save(*turtles)
        val savedList = dao.getAll()

        assertEquals(3, savedList.size)
    }

    @Test
    fun shouldBeReturnsEmptyList(): Unit = runBlocking {
        val list = dao.getAll()
        assertTrue(list.isEmpty())
    }

    @Test
    fun shouldBeReplaceTheItemIfIdIsEquals(): Unit = runBlocking {
        val turtles = createTurtles(2)

        dao.save(turtles.first())
        val firstTurtle = dao.getAll().first()
        assertEquals("id_1", firstTurtle.id)
        assertEquals(1, firstTurtle.energy)
        assertEquals("COMMON", firstTurtle.type)

        dao.save(turtles.last().copy(id = "id_1"))
        val list = dao.getAll()
        val lastTurtle = dao.getAll().last()
        assertEquals(1, list.size)
        assertEquals("id_1", lastTurtle.id)
        assertEquals(2, lastTurtle.energy)
        assertEquals("COMMON", lastTurtle.type)
    }

    private fun createTurtles(quantity: Int = 1): List<TurtleEntity> {
        return (1..quantity).map {
            TurtleEntity(
                id = "id_$it",
                energy = it.toLong(),
                type = "COMMON",
                age = it.toLong(),
                run = it,
                timer = LocalTime.of(12, 0),
            )
        }
    }
}
