package ru.arckontyr.rampractice.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.domain.usecases.GetCharactersUseCase
import ru.arckontyr.rampractice.ui.list.interfaces.CharacterListUIState

class CharacterListViewModel(
    private val getCharacters: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterListUIState>(CharacterListUIState.Loading)
    val uiState: StateFlow<CharacterListUIState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterListUIState.Loading
            getCharacters()
                .onSuccess {
                    _uiState.value = CharacterListUIState.Success(it)
                }
                .onFailure {
                    _uiState.value = CharacterListUIState.Error(
                        it.message ?: "Ошибка загрузки"
                    )
                }
        }
    }
}