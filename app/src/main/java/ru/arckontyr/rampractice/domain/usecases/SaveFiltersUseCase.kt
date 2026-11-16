package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.FilterRepository
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter

class SaveFiltersUseCase(private val repository: FilterRepository) {
    suspend operator fun invoke(filter: CharacterFilter) = repository.save(filter)
}