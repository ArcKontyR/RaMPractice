package ru.arckontyr.rampractice.ui.character.interfaces

import ru.arckontyr.rampractice.domain.model.Character
sealed interface CharacterUIState {
    data object Loading : CharacterUIState
    data class Success(val character: Character) : CharacterUIState
    data class Error(val message: String) : CharacterUIState
}