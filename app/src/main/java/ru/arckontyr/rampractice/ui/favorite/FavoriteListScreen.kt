package ru.arckontyr.rampractice.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ru.arckontyr.rampractice.ui.list.CharacterCardView

import ru.arckontyr.rampractice.domain.model.Character
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteListScreen(
    onCharacterClick: (Int) -> Unit,
    viewModel: FavoriteListViewModel = koinViewModel()
) {
    val favorites by viewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Избранное") })
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (favorites.isEmpty()) {
                Text("Пока пусто. Добавьте персонажей из списка или деталей.")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(favorites, key = { it.id }) { character ->
                        CharacterCardView(
                            character = character,
                            subtitle = "${character.species} / ${character.status.state}",
                            isFavorite = true,
                            onClick = { onCharacterClick(character.id) },
                            onFavoriteClick = { viewModel.onRemove(character.id) }
                        )
                    }
                }
            }
        }
    }
}