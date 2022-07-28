package br.com.messore.tech.turtleracing.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.messore.tech.turtleracing.data.local.model.TurtleEntity

@Dao
interface TurtleDao {

    @Query("SELECT * FROM turtle")
    suspend fun getAll(): List<TurtleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg turtle: TurtleEntity)
}
