package sofascore.pokedex.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.model.network.Network

class DetailPokemonViewModel(application: Application) : AndroidViewModel(application) {

    var detailPokemon: MutableLiveData<DetailPokemonResponse> = MutableLiveData()

    private val network = Network().service

    fun getDetailedTypeAndMoveInfo(id: Int, context: Context) {
        viewModelScope.launch {
            val response = network.getPokemon(id);
            detailPokemon.value = response
        }
    }
}