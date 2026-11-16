package ru.arckontyr.rampractice.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.arckontyr.rampractice.domain.model.Character

@Entity(tableName = "favorites")
data class FavoriteCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originName: String,
    val originUrl: String,
    val locationName: String,
    val locationUrl: String,
    val imageUrl: String,
    val episode: String,
    val url: String,
    val created: String
)