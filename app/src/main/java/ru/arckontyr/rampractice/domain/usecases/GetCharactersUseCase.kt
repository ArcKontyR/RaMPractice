package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.domain.model.Character

class GetCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Result<List<Character>> =
        runCatching { repository.getCharacters() }
}