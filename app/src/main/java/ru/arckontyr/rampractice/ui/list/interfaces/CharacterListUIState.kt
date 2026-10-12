package ru.arckontyr.rampractice.ui.list.interfaces

import ru.arckontyr.rampractice.data.model.Character

sealed interface CharacterListUIState {
    data object Loading : CharacterListUIState
    data class Success(val characters: List<Character>) : CharacterListUIState
    data class Error(val message: String) : CharacterListUIState
}