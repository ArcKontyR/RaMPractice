package ru.arckontyr.rampractice.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.common.LessonReminderScheduler
import ru.arckontyr.rampractice.common.isValidTime
import ru.arckontyr.rampractice.domain.model.Profile
import ru.arckontyr.rampractice.domain.usecases.GetProfileUseCase
import ru.arckontyr.rampractice.domain.usecases.SaveProfileUseCase

class EditProfileViewModel(
    private val getProfile: GetProfileUseCase,
    private val saveProfile: SaveProfileUseCase,
    private val scheduler: LessonReminderScheduler,
) : ViewModel() {

    private val _uiState = MutableStateFlow(Profile.Default)
    val uiState: StateFlow<Profile> = _uiState.asStateFlow()

    private val _timeError = MutableStateFlow<String?>(null)
    val timeError: StateFlow<String?> = _timeError.asStateFlow()

    val canSave: StateFlow<Boolean> = _uiState
        .map { it.lessonTime.isValidTime() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    init {
        viewModelScope.launch {
            _uiState.value = getProfile().first()
        }
    }

    fun onNameChange(v: String) = _uiState.update { it.copy(name = v) }
    fun onJobChange(v: String) = _uiState.update { it.copy(job = v) }
    fun onAvatarChange(v: Uri) = _uiState.update { it.copy(avatarUri = v.toString()) }
    fun onResumeChange(v: String) = _uiState.update { it.copy(resumeUrl = v) }
    fun onLessonTimeChange(v: String) {
        _uiState.update { it.copy(lessonTime = v)}
        _timeError.value = if (v.isValidTime()) null else "Формат: HH:mm"
    }

    fun onReset() { _uiState.value = Profile.Default }

    fun onApply(onDone: () -> Unit) {
        val current = _uiState.value
        if (!current.lessonTime.isValidTime()) {
            _timeError.value = "Формат: HH:mm"
            return
        }
        viewModelScope.launch {
            saveProfile(current)
            scheduler.schedule(current)
            onDone()
        }
    }
}