package ru.arckontyr.rampractice.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.arckontyr.rampractice.data.repository.ProfileRepository
import ru.arckontyr.rampractice.domain.model.Profile

class GetProfileUseCase(private val repository: ProfileRepository) {
    operator fun invoke(): Flow<Profile> = repository.profile
}