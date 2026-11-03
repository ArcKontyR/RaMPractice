package ru.arckontyr.rampractice.ui.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter
import ru.arckontyr.rampractice.domain.usecases.GetFiltersUseCase
import ru.arckontyr.rampractice.domain.usecases.SaveFiltersUseCase

class FilterViewModel(
    private val getFilters: GetFiltersUseCase,
    private val saveFilters: SaveFiltersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterFilter.Default)
    val uiState: StateFlow<CharacterFilter> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = getFilters().first()
        }
    }

    fun onNameChange(v: String) = _uiState.update { it.copy(name = v) }
    fun onStatusChange(v: Status?) = _uiState.update { it.copy(status = v) }
    fun onSpeciesChange(v: String) = _uiState.update { it.copy(species = v) }
    fun onTypeChange(v: String) = _uiState.update { it.copy(type = v) }
    fun onGenderChange(v: Gender?) = _uiState.update { it.copy(gender = v) }

    fun onReset() { _uiState.value = CharacterFilter.Default }

    fun onApply(onDone: () -> Unit) {
        viewModelScope.launch {
            saveFilters(_uiState.value)
            onDone()
        }
    }
}