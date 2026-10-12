package ru.arckontyr.rampractice.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.arckontyr.rampractice.data.remote.dto.CharacterDTO
import ru.arckontyr.rampractice.data.remote.dto.CharactersResponse

interface RaMApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
    ): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDTO

    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}