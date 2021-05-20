package sofascore.pokedex.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun getPagedPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<AllPokemonsResponse>

    @GET("pokemon")
    suspend fun getPagedPokemons(@Query("offset") offset: Int): Response<AllPokemonsResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<AllPokemonsResponse>


}