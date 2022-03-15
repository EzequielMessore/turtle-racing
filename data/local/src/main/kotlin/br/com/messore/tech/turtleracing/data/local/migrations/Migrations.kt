package br.com.messore.tech.turtleracing.data.local.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

internal val MIGRATE_01_02 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase): Unit = with(database) {
        """
            CREATE TABLE IF NOT EXISTS `turtle`(
                `id` TEXT NOT NULL,
                `energy` INTEGER NOT NULL,
                `type` TEXT NOT NULL, 
                `age` INTEGER NOT NULL, 
                `run` INTEGER NOT NULL, 
                `timer` TEXT NOT NULL, 
                PRIMARY KEY(`id`)
            )
        """.trimIndent().also(::execSQL)
    }
}

internal val allMigrations = listOf(MIGRATE_01_02)
