package ru.arckontyr.rampractice.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter

private val Context.filterDataStore by preferencesDataStore(name = "filters")

class FilterDatastore(private val context: Context) {

    private object Keys {
        val NAME = stringPreferencesKey("name")
        val STATUS = stringPreferencesKey("status")
        val SPECIES = stringPreferencesKey("species")
        val TYPE = stringPreferencesKey("type")
        val GENDER = stringPreferencesKey("gender")
    }

    val filters: Flow<CharacterFilter> = context.filterDataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs ->
            CharacterFilter(
                name = prefs[Keys.NAME] ?: "",
                status = prefs[Keys.STATUS]
                    ?.let { stored -> Status.entries.find { it.state == stored } },
                species = prefs[Keys.SPECIES] ?: "",
                type = prefs[Keys.TYPE] ?: "",
                gender = prefs[Keys.GENDER]
                    ?.let { stored -> Gender.entries.find { it.state == stored } }
            )
        }

    suspend fun save(filter: CharacterFilter) {
        context.filterDataStore.edit { prefs ->
            prefs[Keys.NAME] = filter.name
            prefs[Keys.STATUS] = filter.status?.state ?: ""
            prefs[Keys.SPECIES] = filter.species
            prefs[Keys.TYPE] = filter.type
            prefs[Keys.GENDER] = filter.gender?.state ?: ""
        }
    }
}