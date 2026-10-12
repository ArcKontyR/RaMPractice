package ru.arckontyr.rampractice.ui.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.domain.usecases.GetCharactersByIDUseCase
import ru.arckontyr.rampractice.ui.character.interfaces.CharacterUIState
import ru.arckontyr.rampractice.ui.list.interfaces.CharacterListUIState
import ru.arckontyr.rampractice.ui.navigation.Routes

class CharacterViewModel(
    savedStateHandle: SavedStateHandle,
    val getCharactersByIDUseCase: GetCharactersByIDUseCase
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle[Routes.ID])

    private val _uiState = MutableStateFlow<CharacterUIState>(CharacterUIState.Loading)
    val uiState: StateFlow<CharacterUIState> = _uiState.asStateFlow()

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        viewModelScope.launch {
            _uiState.value = CharacterUIState.Loading
            getCharactersByIDUseCase(characterId)
                .onSuccess { _uiState.value = CharacterUIState.Success(it) }
                .onFailure {
                    _uiState.value = CharacterUIState.Error(
                        it.message ?: "Ошибка загрузки"
                    )
                }
        }
    }
}