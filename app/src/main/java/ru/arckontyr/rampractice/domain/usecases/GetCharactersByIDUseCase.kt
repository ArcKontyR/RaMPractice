package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.domain.model.Character

class GetCharactersByIDUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(id: Int): Result<Character> =
        runCatching { repository.getCharacterById(id) }
}