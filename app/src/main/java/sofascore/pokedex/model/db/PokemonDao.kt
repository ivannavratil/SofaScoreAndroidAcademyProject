package sofascore.pokedex.model.db

import androidx.room.Dao
import androidx.room.Query
import sofascore.pokedex.model.Pokemon

@Dao
interface PokemonDao : BaseDao<Pokemon> {

    @Query("SELECT * FROM Pokemon WHERE p_id = :id")
    suspend fun getPokemonById(id: Int): Pokemon?

    @Query("SELECT * FROM Pokemon WHERE p_favourite = 1 ORDER BY p_favourite_number")
    suspend fun getAllFavoritePokemonsSorted(): List<Pokemon>

    @Query("SELECT p_favourite FROM Pokemon WHERE p_id = :id")
    suspend fun isPokemonFavourite(id: Int): Boolean?

    @Query("SELECT p_favourite_number FROM Pokemon WHERE p_id = :id")
    suspend fun getFavouriteNumber(id: Int): Int

    @Query("SELECT coalesce(MAX(p_favourite_number),0) FROM Pokemon")
    suspend fun getMaxFavouriteCityOrder(): Int

    @Query("UPDATE Pokemon SET p_favourite=:favourite, p_favourite_number = :favouriteNumber WHERE p_id = :id")
    suspend fun updatePokemonFavStatusAndOrder(
        id: Int,
        favourite: Boolean,
        favouriteNumber: Int?
    )

    @Query("UPDATE Pokemon SET p_favourite=0, p_favourite_number = NULL")
    suspend fun clearFavouritePokemons()

}