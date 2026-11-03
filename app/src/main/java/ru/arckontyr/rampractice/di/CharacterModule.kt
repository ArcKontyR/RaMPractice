package ru.arckontyr.rampractice.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.arckontyr.rampractice.data.datastore.FilterDatastore
import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.data.repository.FilterRepository
import ru.arckontyr.rampractice.domain.usecases.GetCharactersByIDUseCase
import ru.arckontyr.rampractice.domain.usecases.GetCharactersUseCase
import ru.arckontyr.rampractice.domain.usecases.GetFavoriteCharactersUseCase
import ru.arckontyr.rampractice.domain.usecases.GetFiltersUseCase
import ru.arckontyr.rampractice.domain.usecases.SaveFiltersUseCase
import ru.arckontyr.rampractice.network.RaMApi
import ru.arckontyr.rampractice.ui.character.CharacterViewModel
import ru.arckontyr.rampractice.ui.filters.FilterBadgeCache
import ru.arckontyr.rampractice.ui.filters.FilterViewModel
import ru.arckontyr.rampractice.ui.list.CharacterListViewModel

val characterModule = module {

    single(named("appScope")) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    viewModel { CharacterViewModel(get(), get()) }
    viewModel { CharacterListViewModel(get(), get(),get()) }
    viewModel { FilterViewModel(get(), get()) }

    single { get<Retrofit>().create(RaMApi::class.java) }

    single { FilterDatastore(androidContext()) }

    single { CharacterRepository(get(), get()) }
    single { FilterRepository(get()) }
    single<CoroutineDispatcher> { Dispatchers.IO }

    single { FilterBadgeCache(get(), get(named("appScope"))) }

    single { GetCharactersByIDUseCase(get()) }
    single { GetCharactersUseCase(get()) }
    single { GetFavoriteCharactersUseCase(get()) }
    single { GetFiltersUseCase(get()) }
    single { SaveFiltersUseCase(get()) }
}