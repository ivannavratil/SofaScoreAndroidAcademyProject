package sofascore.pokedex.model.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sofascore.pokedex.model.*
import sofascore.pokedex.model.db.DetailPokemonResponse

interface PokedexService {

    @GET("pokemon")
    suspend fun getPagedPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): AllPokemonsResponse

    @GET("pokemon")
    suspend fun getPagedPokemons(@Query("offset") offset: Int): AllPokemonsResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): DetailPokemonResponse

    @GET("type/{id}")
    suspend fun getTypeDetail(@Path("id") id: Int): TypeDetailResponse

    @GET("move/{id}")
    suspend fun getMoveDetail(@Path("id") id: Int): TypeDetailMoveResponse

    @GET("pokemon/?offset=0&limit=10000")
    suspend fun allPokemons(): AllPokemonsResponse

    @GET("evolution-chain/{id}")
    suspend fun getEvolution(@Path("id") id: Int): PokemonEvolutionResponse

    @GET("pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") id: Int): PokemonSpeciesResponse

}