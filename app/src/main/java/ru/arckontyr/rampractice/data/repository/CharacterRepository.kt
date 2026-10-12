package ru.arckontyr.rampractice.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.arckontyr.rampractice.data.mapper.toDomain
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.network.RaMApi

class CharacterRepository(
    private val api: RaMApi,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getCharacters(): List<Character> =
        withContext(ioDispatcher) {
            api.getCharacters(LIMIT).results.map { it.toDomain() }
        }

    suspend fun getCharacterById(id: Int): Character =
        withContext(ioDispatcher) {
            api.getCharacter(id).toDomain()
        }

    companion object{
        const val LIMIT = 50
    }
}