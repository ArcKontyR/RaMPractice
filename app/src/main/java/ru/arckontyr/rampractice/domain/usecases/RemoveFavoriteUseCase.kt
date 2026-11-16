package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.FavoriteCharactersRepository

class RemoveFavoriteUseCase(private val repository: FavoriteCharactersRepository) {
    suspend operator fun invoke(id: Int) = repository.remove(id)
}