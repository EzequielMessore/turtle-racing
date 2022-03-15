package br.com.messore.tech.turtleracing.data.local

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.messore.tech.turtleracing.data.local.database.AppDatabase
import br.com.messore.tech.turtleracing.data.local.extensions.insert
import br.com.messore.tech.turtleracing.data.local.migrations.MIGRATE_01_02
import br.com.messore.tech.turtleracing.data.local.migrations.allMigrations
import br.com.messore.tech.turtleracing.data.local.model.TurtleEntity
import br.com.messore.tech.turtleracing.domain.model.TurtleType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalTime
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val databaseName = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java,
    )

    @Test
    @Throws(IOException::class)
    fun migrateAll() {
        helper.createDatabase(databaseName, 1).close()

        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java,
            databaseName
        ).addMigrations(*allMigrations.toTypedArray()).build().apply {
            openHelper.writableDatabase.close()
        }
    }

    @Test
    fun migrate_01_02() {
        helper.createDatabase(databaseName, 1).apply {
            insert("token") {
                put("token", UUID.randomUUID().toString())
            }
            close()
        }

        val db = helper.runMigrationsAndValidate(databaseName, 2, true, MIGRATE_01_02)

        db.insert("turtle") {
            put("id", "61e89")
            put("energy", 100)
            put("type", TurtleType.COMMON.name)
            put("age", 12)
            put("run", 2)
            put("timer", "12:12:12")
        }

        var turtle: TurtleEntity? = null
        val cursor = db.query("select * from turtle")
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val energy = cursor.getLong(cursor.getColumnIndex("energy"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val age = cursor.getLong(cursor.getColumnIndex("age"))
                val run = cursor.getInt(cursor.getColumnIndex("run"))
                val timer = cursor.getString(cursor.getColumnIndex("timer"))
                turtle = TurtleEntity(id, energy, type, age, run , LocalTime.parse(timer))
            } while (cursor.moveToNext())
        }

        assertNotNull(turtle)
        assertEquals("61e89", turtle.id)
        assertEquals(100L, turtle.energy)
        assertEquals(TurtleType.COMMON.name, turtle.type)
        assertEquals(12L, turtle.age)
        assertEquals(2, turtle.run)
        assertEquals(LocalTime.of(12 , 12, 12), turtle.timer)
    }
}
