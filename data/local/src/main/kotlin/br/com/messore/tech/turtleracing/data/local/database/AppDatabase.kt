package br.com.messore.tech.turtleracing.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.messore.tech.turtleracing.data.local.converters.LocalTimeConverter
import br.com.messore.tech.turtleracing.data.local.dao.TokenDao
import br.com.messore.tech.turtleracing.data.local.dao.TurtleDao
import br.com.messore.tech.turtleracing.data.local.model.TokenEntity
import br.com.messore.tech.turtleracing.data.local.model.TurtleEntity

@TypeConverters(LocalTimeConverter::class)
@Database(entities = [TokenEntity::class, TurtleEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun turtleDao() : TurtleDao
}
