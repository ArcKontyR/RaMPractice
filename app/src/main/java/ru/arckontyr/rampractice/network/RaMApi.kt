package ru.arckontyr.rampractice.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.arckontyr.rampractice.data.enums.Status
import ru.arckontyr.rampractice.data.remote.dto.CharacterDTO
import ru.arckontyr.rampractice.data.remote.dto.CharactersResponse

interface RaMApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDTO

    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}