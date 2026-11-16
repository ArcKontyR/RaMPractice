package ru.arckontyr.rampractice.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.arckontyr.rampractice.data.local.entities.FavoriteCharacter

@Dao
interface FavoriteCharacterDao {

    @Query("SELECT * FROM favorites ORDER BY name")
    fun observeAll(): Flow<List<FavoriteCharacter>>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getById(id: Int): FavoriteCharacter?

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteCharacter)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteById(id: Int)
}