package br.com.messore.tech.turtleracing.data.local.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.messore.tech.turtleracing.data.local.extensions.plus

internal val MIGRATE_01_02 = migrate(1..2) {
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
    """
}

internal val MIGRATE_02_03 = migrate(2..3) {
    """
        ALTER TABLE `turtle` ADD `visibleId` TEXT DEFAULT 1 NOT NULL;
    """
}


private fun migrate(version: IntRange, sql: () -> String) =
    object : Migration(version.first, version.last) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(sql())
        }
    }


internal val allMigrations = MIGRATE_01_02 + MIGRATE_02_03

