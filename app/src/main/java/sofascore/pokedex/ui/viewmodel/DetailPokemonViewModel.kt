package sofascore.pokedex.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.Evolution
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.model.PokemonEvolutionResponse
import sofascore.pokedex.model.db.AppDatabase
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.model.network.Network
import sofascore.pokedex.other.Util

class DetailPokemonViewModel(application: Application) : AndroidViewModel(application) {

    var detailPokemon: MutableLiveData<DetailPokemonResponse> = MutableLiveData()

    var favourite: MutableLiveData<Boolean> = MutableLiveData()

    var evaluation: MutableLiveData<ArrayList<List<Evolution>>> = MutableLiveData()

    private val network = Network().service

    private fun getEvolution() {

        val listOfEvaluations: ArrayList<List<Evolution>> = ArrayList()

        viewModelScope.launch {

            val url = detailPokemon.value!!.species.url;

            val speciesID = Util.getId(url);
            val evolutionChainID: Int = Util.getId(network.getSpecies(speciesID).evolutionChain.url)
            val evolution: PokemonEvolutionResponse = network.getEvolution(evolutionChainID);


            val listWithFirst: ArrayList<Evolution> = ArrayList()

            val id = Util.getId(evolution.chain.species.url);
            listWithFirst.add(
                Evolution(
                    id,
                    evolution.chain.species.name,
                    network.getPokemon(id).types,
                    null
                )
            );


            for (ev in evolution.chain.evolvesTo) {

                val temp: ArrayList<Evolution> = ArrayList(listWithFirst)

                val id = Util.getId(ev.species.url);
                temp.add(
                    Evolution(
                        id,
                        ev.species.name,
                        network.getPokemon(id).types,
                        ev.evolutionDetails[0].minLevel
                    )
                );




                for (e in ev.evolvesTo) {
                    val id1 = Util.getId(e.species.url);
                    temp.add(
                        Evolution(
                            id1,
                            e.species.name,
                            network.getPokemon(id1).types,
                            e.evolutionDetails[0].minLevel
                        )
                    );

                }

                listOfEvaluations.add(temp)
            }

            evaluation.value = listOfEvaluations
        }
    }

    fun getInfo(id: Int, context: Context) {
        viewModelScope.launch {
            val response = network.getPokemon(id)
            detailPokemon.value = response

            favourite.value =
                AppDatabase.getDatabase(context).PokemonDao().isPokemonFavourite(id) == true

            getEvolution()
        }
    }

    fun refreshFavoriteStatus(id: Int, context: Context) {
        viewModelScope.launch {
            favourite.value =
                AppDatabase.getDatabase(context).PokemonDao().isPokemonFavourite(id) == true
        }
    }

    fun flipFavourite(context: Context) {

        viewModelScope.launch {
            val database = AppDatabase.getDatabase(context)
            val value = detailPokemon.value!!
            val id = value.id

            if (database.PokemonDao().getPokemonById(id) == null) {
                val next = database.PokemonDao().getMaxFavouritePokemonOrder() + 1
                val pokemon = Pokemon(id, value.name, true, next)
                database.PokemonDao().insert(pokemon)
            } else {
                if (favourite.value!!) {
                    database.PokemonDao().updatePokemonFavStatusAndOrder(id, false, -1)

                } else {
                    val next = database.PokemonDao().getMaxFavouritePokemonOrder() + 1
                    database.PokemonDao().updatePokemonFavStatusAndOrder(id, true, next)
                }
            }


            favourite.value = !favourite.value!!
        }

    }
}