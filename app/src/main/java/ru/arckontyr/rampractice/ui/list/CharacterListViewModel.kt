package ru.arckontyr.rampractice.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.domain.model.Character
import ru.arckontyr.rampractice.domain.model.filters.CharacterFilter
import ru.arckontyr.rampractice.domain.usecases.AddFavoriteUseCase
import ru.arckontyr.rampractice.domain.usecases.GetCharactersUseCase
import ru.arckontyr.rampractice.domain.usecases.GetFavoriteCharactersUseCase
import ru.arckontyr.rampractice.domain.usecases.GetFiltersUseCase
import ru.arckontyr.rampractice.ui.filters.FilterBadgeCache
import ru.arckontyr.rampractice.ui.list.interfaces.CharacterListUIState

class CharacterListViewModel(
    private val getCharacters: GetCharactersUseCase,
    private val getFavorite: GetFavoriteCharactersUseCase,
    private val getFilters: GetFiltersUseCase,
    private val addFavorite: AddFavoriteUseCase,
    private val badgeCache: FilterBadgeCache
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterListUIState>(CharacterListUIState.Loading)
    val uiState: StateFlow<CharacterListUIState> = _uiState.asStateFlow()
    val favoriteIds = getFavorite()
        .map { fav -> fav.map { it.id }}
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

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

    fun onFavoriteClick(character: Character) {
        viewModelScope.launch { addFavorite(character) }
    }
}