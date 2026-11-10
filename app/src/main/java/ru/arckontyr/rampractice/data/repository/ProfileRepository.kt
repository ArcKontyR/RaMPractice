package ru.arckontyr.rampractice.data.repository

import kotlinx.coroutines.flow.Flow
import ru.arckontyr.rampractice.data.ResumeDownloader
import ru.arckontyr.rampractice.data.datastore.ProfileDatastore
import ru.arckontyr.rampractice.domain.model.Profile

class ProfileRepository(
    private val dataStore: ProfileDatastore,
    private val resumeDownloader: ResumeDownloader,
)  {

    val profile: Flow<Profile> = dataStore.profile

    suspend fun save(profile: Profile) = dataStore.save(profile)

    fun downloadResume(url: String): Unit = resumeDownloader.downloadResume(url)
}