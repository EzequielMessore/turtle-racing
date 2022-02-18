package br.com.messore.tech.turtleracing.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.messore.tech.turtleracing.data.local.model.TokenEntity

@Dao
interface TokenDao {
    @Query("SELECT * FROM token LIMIT 1")
    suspend fun getToken(): TokenEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(token: TokenEntity): Long

}
