package sofascore.pokedex.model.db

import androidx.room.Query
import sofascore.pokedex.model.PokemonNamePhoto

interface PokemonDao:BaseDao<PokemonNamePhoto> {

    @Query("SELECT * FROM PokemonNamePhoto WHERE id = :id")
    suspend fun getPokemonById(id:Int):PokemonNamePhoto

}