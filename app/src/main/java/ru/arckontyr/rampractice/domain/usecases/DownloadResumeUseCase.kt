package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.ProfileRepository

class DownloadResumeUseCase(private val repository: ProfileRepository) {
    operator fun invoke(url: String): Unit = repository.downloadResume(url)
}