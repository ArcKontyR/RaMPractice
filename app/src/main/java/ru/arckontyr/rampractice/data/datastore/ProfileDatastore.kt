package ru.arckontyr.rampractice.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.arckontyr.rampractice.domain.model.Profile

private val Context.profileDataStore by preferencesDataStore(name = "user_profile")

class ProfileDatastore(
    private val context: Context
) {
    private object Keys {
        val NAME = stringPreferencesKey("p_name")
        val JOB = stringPreferencesKey("p_job")
        val AVATAR = stringPreferencesKey("p_avatar")
        val RESUME = stringPreferencesKey("p_resume")
    }

    val profile: Flow<Profile> = context.profileDataStore.data
        .catch { emit(emptyPreferences()) }
        .map { prefs ->
            Profile(
                name = prefs[Keys.NAME] ?: "",
                job = prefs[Keys.JOB] ?: "",
                avatarUri = prefs[Keys.AVATAR] ?: "",
                resumeUrl = prefs[Keys.RESUME] ?: ""
            )
        }

    suspend fun save(profile: Profile) {
        context.profileDataStore.edit { prefs ->
            prefs[Keys.NAME] = profile.name
            prefs[Keys.JOB] = profile.job
            prefs[Keys.AVATAR] = profile.avatarUri
            prefs[Keys.RESUME] = profile.resumeUrl
        }
    }
}