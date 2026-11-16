package ru.arckontyr.rampractice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.arckontyr.rampractice.data.local.dao.FavoriteCharacterDao
import ru.arckontyr.rampractice.data.local.entities.FavoriteCharacter

@Database(
    entities = [FavoriteCharacter::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteCharactersDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteCharacterDao
}