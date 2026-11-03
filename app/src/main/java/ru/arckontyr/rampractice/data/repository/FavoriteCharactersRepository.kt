package ru.arckontyr.rampractice.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.arckontyr.rampractice.data.local.dao.FavoriteCharacterDao
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.data.mapper.toDomain
import ru.arckontyr.rampractice.data.mapper.toEntity

class FavoriteCharactersRepository(
    private val dao: FavoriteCharacterDao,
) {

    val favorites: Flow<List<Character>> =
        dao.observeAll().map { list -> list.map { it.toDomain() } }

    suspend fun getFavorite(id: Int) = dao.getById(id)?.toDomain()

    suspend fun add(character: Character){
        if (dao.isFavorite(character.id))
            remove(character.id)
        else
            dao.insert(character.toEntity())
    }

    suspend fun remove(id: Int) {
        dao.deleteById(id)
    }
}