package ru.arckontyr.rampractice.ui.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import ru.arckontyr.rampractice.data.enums.Gender
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.ui.common.DropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    onBack: () -> Unit,
    onDone: () -> Unit,
    viewModel: FilterViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Фильтр") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                OutlinedTextField(
                    value = uiState.name, onValueChange = viewModel::onNameChange,
                    label = { Text("Имя") }, modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    label = "Статус",
                    options = Status.entries,
                    selected = uiState.status,
                    optionLabel = { it.state },
                    onSelect = viewModel::onStatusChange
                )
                OutlinedTextField(
                    value = uiState.species, onValueChange = viewModel::onSpeciesChange,
                    label = { Text("Вид") }, modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.type, onValueChange = viewModel::onTypeChange,
                    label = { Text("Тип") }, modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    label = "Пол",
                    options = Gender.entries,
                    selected = uiState.gender,
                    optionLabel = { it.state },
                    onSelect = viewModel::onGenderChange
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = viewModel::onReset,
                        modifier = Modifier.weight(1f)
                    ) { Text("Сбросить") }
                    Spacer(Modifier.width(12.dp))
                    Button(
                        onClick = { viewModel.onApply(onDone) },
                        modifier = Modifier.weight(1f)
                    ) { Text("Готово") }
                }
            }

        }
    }
}