package ru.arckontyr.rampractice.ui.filters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.arckontyr.rampractice.data.repository.FilterRepository

class FilterBadgeCache(
    filterRepository: FilterRepository,
    appScope: CoroutineScope
) {
    private val _show = MutableStateFlow(false)
    val show: StateFlow<Boolean> = _show.asStateFlow()

    init {
        appScope.launch {
            filterRepository.filters.collect { filter ->
                _show.value = !filter.isDefault
            }
        }
    }
}