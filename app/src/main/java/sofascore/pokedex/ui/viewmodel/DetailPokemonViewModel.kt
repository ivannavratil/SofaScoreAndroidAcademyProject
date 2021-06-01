package sofascore.pokedex.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.db.AppDatabase
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.model.network.Network

class DetailPokemonViewModel(application: Application) : AndroidViewModel(application) {

    var detailPokemon: MutableLiveData<DetailPokemonResponse> = MutableLiveData()

    var favourite: MutableLiveData<Boolean> = MutableLiveData()

    private val network = Network().service

    fun getDetailedTypeAndMoveInfo(id: Int, context: Context) {
        viewModelScope.launch {
            val response = network.getPokemon(id);
            detailPokemon.value = response

            favourite.value = AppDatabase.getDatabase(context).PokemonDao().isPokemonFavourite(id)
        }
    }

    fun flipFavourite(context: Context) {

        viewModelScope.launch {
            val database = AppDatabase.getDatabase(context)
            val id = detailPokemon.value!!.id

            if (favourite.value!!) {
                database.PokemonDao().updatePokemonFavStatusAndOrder(id, false, -1)

            } else {
                val next = database.PokemonDao().getMaxFavouriteCityOrder() + 1
                database.PokemonDao().updatePokemonFavStatusAndOrder(id, true, next)
            }
            favourite.value = !favourite.value!!
        }

    }
}