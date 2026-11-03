package ru.arckontyr.rampractice.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter
import ru.arckontyr.rampractice.domain.usecases.GetCharactersUseCase
import ru.arckontyr.rampractice.domain.usecases.GetFiltersUseCase
import ru.arckontyr.rampractice.ui.filters.FilterBadgeCache
import ru.arckontyr.rampractice.ui.list.interfaces.CharacterListUIState

class CharacterListViewModel(
    private val getCharacters: GetCharactersUseCase,
    private val getFilters: GetFiltersUseCase,
    private val badgeCache: FilterBadgeCache
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterListUIState>(CharacterListUIState.Loading)
    val uiState: StateFlow<CharacterListUIState> = _uiState.asStateFlow()

    val showBadge: StateFlow<Boolean> = badgeCache.show

    init {
        viewModelScope.launch {
            getFilters().collect { filter -> loadCharacters(filter) }
        }
    }

    private fun loadCharacters(filter: CharacterFilter) {
        viewModelScope.launch {
            _uiState.value = CharacterListUIState.Loading
            getCharacters(filter)
                .map{chars -> chars.map{ it.toUi() } }
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

private fun Character.toUi(): CharacterCard = CharacterCard(
    id = id,
    name = name,
    subtitle = "$species / ${status.state}",
    location = location,
    imageUrl = imageUrl
)