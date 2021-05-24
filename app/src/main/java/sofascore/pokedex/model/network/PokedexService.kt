package sofascore.pokedex.model.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sofascore.pokedex.model.AllPokemonsResponse

interface PokedexService {

    @GET("pokemon")
    suspend fun getPagedPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): AllPokemonsResponse

    @GET("pokemon")
    suspend fun getPagedPokemons(@Query("offset") offset: Int): AllPokemonsResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): AllPokemonsResponse


}