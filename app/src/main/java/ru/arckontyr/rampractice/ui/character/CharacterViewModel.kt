package ru.arckontyr.rampractice.ui.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.arckontyr.rampractice.data.model.Character
import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.ui.navigation.Routes

class CharacterViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle[Routes.ID])

    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character.asStateFlow()

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        _character.value = CharacterRepository.getCharacterById(characterId)
    }
}