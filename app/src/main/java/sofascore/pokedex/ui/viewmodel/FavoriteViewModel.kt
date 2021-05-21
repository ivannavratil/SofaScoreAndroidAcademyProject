package sofascore.pokedex.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sofascore.pokedex.model.Pokemon

class FavoriteViewModel : ViewModel() {

   var favoritePokemons:MutableLiveData<List<Pokemon>> = MutableLiveData(arrayListOf())

    fun loadFavoritePokemons(context: Context) {
        //TODO: implement
//        viewModelScope.launch {
//            val db = AppDatabase.getDatabase(context)
//            val allFavoritePokemons = db.PokemonDao().getAllFavoritePokemons()
//            favoritePokemons.value = allFavoritePokemons
//        }
    }

    init{
        favoritePokemons.value = mutableListOf(
            Pokemon(
            169,"Pero","/15"
        ), Pokemon(
            559,"Peroooo","/17"
        ) )

    }

    fun flipFavourite(context: Context, pokemon: Pokemon) {
        //TODO: implement
    }

    fun swapFavoritePokemonsOrder(item: Pokemon, item1: Pokemon, requireContext: Context) {
       // TODO("Not yet implemented")
    }
}