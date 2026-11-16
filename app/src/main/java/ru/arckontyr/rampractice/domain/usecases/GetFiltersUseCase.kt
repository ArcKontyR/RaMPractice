package ru.arckontyr.rampractice.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.arckontyr.rampractice.data.repository.FilterRepository
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter

class GetFiltersUseCase(private val repository: FilterRepository) {
    operator fun invoke(): Flow<CharacterFilter> = repository.filters
}