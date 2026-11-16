package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.FavoriteCharactersRepository
import ru.arckontyr.rampractice.domain.model.Character

class AddFavoriteUseCase(private val repository: FavoriteCharactersRepository) {
    suspend operator fun invoke(character: Character) = repository.add(character)
}