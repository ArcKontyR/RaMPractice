package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.domain.extensions.runSuspendCatching
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter

class GetFavoriteCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(filter: CharacterFilter): Result<List<Character>> =
        runSuspendCatching { repository.getCharacters(filter) }
}