package ru.arckontyr.rampractice.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.arckontyr.rampractice.data.repository.CharacterRepository
import ru.arckontyr.rampractice.domain.usecases.GetCharactersByIDUseCase
import ru.arckontyr.rampractice.domain.usecases.GetCharactersUseCase
import ru.arckontyr.rampractice.network.RaMApi
import ru.arckontyr.rampractice.ui.character.CharacterViewModel
import ru.arckontyr.rampractice.ui.list.CharacterListViewModel

val characterModule = module {
    viewModel { CharacterViewModel(get(), get()) }
    viewModel { CharacterListViewModel(get()) }

    single { get<Retrofit>().create(RaMApi::class.java) }

    single { CharacterRepository(get(), get()) }
    single<CoroutineDispatcher> { Dispatchers.IO }

    single { GetCharactersByIDUseCase(get()) }
    single { GetCharactersUseCase(get()) }
}