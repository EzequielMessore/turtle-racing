package br.com.messore.tech.turtleracing.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.messore.tech.turtleracing.data.local.database.AppDatabase
import br.com.messore.tech.turtleracing.data.local.model.TokenEntity
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TokenDaoTest {

    private lateinit var database: AppDatabase
    private val dao by lazy { database.tokenDao() }
    private val token = "702c4bdb-e75d-45b0-95c3-dadee4f29cf3"

    @BeforeEach
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @AfterEach
    fun close() {
        database.close()
    }

    @Test
    fun saveTokenAndRetrieveTheFirst(): Unit = runBlocking {
        saveToken()

        val entity = dao.getToken()
        assertAll(
            { assertNotNull(entity) },
            { assertEquals(token, entity?.token) }
        )
    }

    @Test
    fun shouldBeNullWhenThereIsNoLocalToken(): Unit = runBlocking {
        val entity = dao.getToken()

        assertNull(entity)
    }

    private suspend fun saveToken() {
        dao.save(TokenEntity(token = token))
    }
}
