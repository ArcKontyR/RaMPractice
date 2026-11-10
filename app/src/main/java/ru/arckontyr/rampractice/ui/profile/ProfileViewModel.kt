package ru.arckontyr.rampractice.ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import ru.arckontyr.rampractice.domain.model.Profile
import ru.arckontyr.rampractice.domain.usecases.DownloadResumeUseCase
import ru.arckontyr.rampractice.domain.usecases.GetProfileUseCase

class ProfileViewModel(
    getProfile: GetProfileUseCase,
    private val downloadResumeUseCase: DownloadResumeUseCase,
) : ViewModel() {

    val profile: StateFlow<Profile?> = getProfile()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    private val _openPdf = MutableSharedFlow<Uri>(extraBufferCapacity = 1)
    val openPdf: SharedFlow<Uri> = _openPdf.asSharedFlow()

    fun downloadResume(url: String) {
        downloadResumeUseCase(url)
    }


}