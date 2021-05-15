package sofascore.pokedex.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<List<String>>


}