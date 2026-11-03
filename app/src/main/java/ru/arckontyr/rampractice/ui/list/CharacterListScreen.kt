package ru.arckontyr.rampractice.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.toList
import ru.arckontyr.rampractice.ui.list.interfaces.CharacterListUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    onCharacterClick: (Int) -> Unit,
    onOpenFilters: () -> Unit,
    viewModel: CharacterListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val showBadge by viewModel.showBadge.collectAsState()
    val favoriteIds by viewModel.favoriteIds.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Персонажи Rick and Morty") },
                actions = {
                    BadgedBox(
                        badge = { if (showBadge) Badge() }
                    ) {
                            IconButton(onClick = onOpenFilters) {
                                Icon(Icons.Default.FilterList, contentDescription = "Фильтры")
                            }
                        }
                    })
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is CharacterListUIState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                is CharacterListUIState.Error -> {
                    Text(
                        text = state.message,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                is CharacterListUIState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.characters, key = { it.id }) { character ->
                            CharacterCardView(
                                character = character,
                                subtitle = "${character.species} / ${character.status.state}",
                                isFavorite = character.id in favoriteIds,
                                onClick = { onCharacterClick(character.id) },
                                onFavoriteClick = { viewModel.onFavoriteClick(character) }
                            )
                        }
                    }
                }
            }
        }
    }
}