package sofascore.pokedex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.PokemonNamePhoto
import sofascore.pokedex.model.network.Network

class SearchResultsViewModel : ViewModel() {

    var allResults: MutableLiveData<List<PokemonNamePhoto>> = MutableLiveData()

    private val network = Network().service


    fun loadPokemons() {
        viewModelScope.launch {
            val allPokemons = network.allPokemons();
            allResults.value = allPokemons.results
        }
    }


}