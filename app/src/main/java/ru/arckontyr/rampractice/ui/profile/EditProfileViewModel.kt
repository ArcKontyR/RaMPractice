package ru.arckontyr.rampractice.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.domain.model.Profile
import ru.arckontyr.rampractice.domain.usecases.GetProfileUseCase
import ru.arckontyr.rampractice.domain.usecases.SaveProfileUseCase

class EditProfileViewModel(
    private val getProfile: GetProfileUseCase,
    private val saveProfile: SaveProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(Profile.Default)
    val uiState: StateFlow<Profile> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = getProfile().first()
        }
    }

    fun onNameChange(v: String) = _uiState.update { it.copy(name = v) }
    fun onJobChange(v: String) = _uiState.update { it.copy(job = v) }
    fun onAvatarChange(v: Uri) = _uiState.update { it.copy(avatarUri = v.toString()) }
    fun onResumeChange(v: String) = _uiState.update { it.copy(resumeUrl = v) }

    fun onReset() { _uiState.value = Profile.Default }

    fun onApply(onDone: () -> Unit) {
        viewModelScope.launch {
            saveProfile(_uiState.value)
            onDone()
        }
    }
}