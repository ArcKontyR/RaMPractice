package ru.arckontyr.rampractice.data.repository

import kotlinx.coroutines.flow.Flow
import ru.arckontyr.rampractice.data.datastore.FilterDatastore
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter

class FilterRepository(
    private val dataStore: FilterDatastore
) {
    val filters: Flow<CharacterFilter> = dataStore.filters
    suspend fun save(filter: CharacterFilter) = dataStore.save(filter)
}