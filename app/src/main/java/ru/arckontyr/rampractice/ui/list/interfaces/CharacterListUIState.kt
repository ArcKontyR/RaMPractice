package ru.arckontyr.rampractice.ui.list.interfaces

import ru.arckontyr.rampractice.ui.list.CharacterCard

sealed interface CharacterListUIState {
    data object Loading : CharacterListUIState
    data class Success(val characters: List<CharacterCard>) : CharacterListUIState
    data class Error(val message: String) : CharacterListUIState
}