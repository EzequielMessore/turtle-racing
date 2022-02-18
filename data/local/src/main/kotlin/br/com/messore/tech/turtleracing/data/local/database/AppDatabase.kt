package br.com.messore.tech.turtleracing.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.messore.tech.turtleracing.data.local.converters.DateTimeConverter
import br.com.messore.tech.turtleracing.data.local.dao.TokenDao
import br.com.messore.tech.turtleracing.data.local.model.TokenEntity

@TypeConverters(DateTimeConverter::class)
@Database(entities = [TokenEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}
