package ru.arckontyr.rampractice.domain.usecases

import ru.arckontyr.rampractice.data.repository.ProfileRepository
import ru.arckontyr.rampractice.domain.model.Profile

class SaveProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(profile: Profile) = repository.save(profile)
}