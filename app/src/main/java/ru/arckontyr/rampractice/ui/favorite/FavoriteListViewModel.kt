package ru.arckontyr.rampractice.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.domain.usecases.GetFavoriteCharactersUseCase
import ru.arckontyr.rampractice.domain.usecases.RemoveFavoriteUseCase
import kotlin.collections.emptyList
import ru.arckontyr.rampractice.domain.model.Character

class FavoriteListViewModel(
    private val getFavorites: GetFavoriteCharactersUseCase,
    private val removeFavorite: RemoveFavoriteUseCase
) : ViewModel() {

    val favorites: StateFlow<List<Character>> = getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onRemove(id: Int) {
        viewModelScope.launch { removeFavorite(id) }
    }
}