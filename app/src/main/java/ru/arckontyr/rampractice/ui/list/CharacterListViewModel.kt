package ru.arckontyr.rampractice.ui.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.ui.list.interfaces.CharacterListUIState

class CharacterListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterListUIState>(CharacterListUIState.Loading)
    val uiState: StateFlow<CharacterListUIState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        val characters = CharacterRepository.characters
        _uiState.value = CharacterListUIState.Success(characters)
    }
}