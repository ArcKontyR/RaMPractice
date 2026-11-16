package ru.arckontyr.rampractice.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.arckontyr.rampractice.data.repository.FavoriteCharactersRepository
import ru.arckontyr.rampractice.domain.model.Character

class GetFavoriteCharactersUseCase(private val repo: FavoriteCharactersRepository) {
    operator fun invoke(): Flow<List<Character>> = repo.favorites
}