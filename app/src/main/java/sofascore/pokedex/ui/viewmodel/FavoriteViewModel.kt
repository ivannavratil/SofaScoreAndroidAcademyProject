package sofascore.pokedex.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.model.db.AppDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    var favoritePokemons: MutableLiveData<List<Pokemon>> = MutableLiveData(arrayListOf())

    init {
        viewModelScope.launch {
            favoritePokemons.value =
                AppDatabase.getDatabase(application).PokemonDao()
                    .getAllFavoritePokemonsSorted()
        }
    }

    fun loadFavoritePokemons(context: Context) {
        viewModelScope.launch {
            favoritePokemons.value =
                AppDatabase.getDatabase(context).PokemonDao()
                    .getAllFavoritePokemonsSorted()
        }
    }

    fun flipFavourite(context: Context, pokemon: Pokemon) {
        viewModelScope.launch {
            val database = AppDatabase.getDatabase(context)


            pokemon.favouriteNumber = when (pokemon.favourite) {
                true -> null
                false -> database.PokemonDao().getMaxFavouritePokemonOrder() + 1
            }

            pokemon.favourite = !pokemon.favourite
            database.PokemonDao().insert(pokemon)
        }
    }

    fun swapFavoritePokemonsOrder(p1: Pokemon, p2: Pokemon, context: Context) {
        viewModelScope.launch {
            val db = AppDatabase.getDatabase(context)
            db.PokemonDao()
                .updatePokemonFavStatusAndOrder(p1.id, p1.favourite, p2.favouriteNumber!!)
            db.PokemonDao()
                .updatePokemonFavStatusAndOrder(p2.id, p1.favourite, p1.favouriteNumber!!)
            val temp = p1.favouriteNumber
            p1.favouriteNumber = p2.favouriteNumber
            p2.favouriteNumber = temp
        }
    }

    fun clearFavouritePokemons() {
        viewModelScope.launch {
            AppDatabase.getDatabase(getApplication()).PokemonDao().clearFavouritePokemons()
        }
    }
}
