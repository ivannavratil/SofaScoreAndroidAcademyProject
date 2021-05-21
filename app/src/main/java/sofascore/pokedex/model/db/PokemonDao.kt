package sofascore.pokedex.model.db

import androidx.room.Dao
import androidx.room.Query
import sofascore.pokedex.model.Pokemon

@Dao
interface PokemonDao : BaseDao<Pokemon> {

    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getPokemonById(id: Int): Pokemon

    @Query("SELECT * FROM Pokemon WHERE favourite = 1")
    suspend fun getAllFavoritePokemons(): List<Pokemon>


}