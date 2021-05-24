package sofascore.pokedex.model

import android.app.Application
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sofascore.pokedex.Util
import sofascore.pokedex.model.db.AppDatabase
import sofascore.pokedex.model.network.Network


class PokemonNamePhotoDataSource(
    private val scope: CoroutineScope,
    private val application: Application
) :
    PageKeyedDataSource<Int, Pokemon>() {

    private val apiService = Network().service

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pokemon>
    ) {
        scope.launch {

            val (response: AllPokemonsResponse, pokemonList) = responseToPokemonList(0)

            callback.onResult(
                pokemonList, null,
                if (response.next == null) null else (Util.getOffset(response.next) + 1)
            )
        }
    }


    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pokemon>
    ) {
        scope.launch {
            val (response: AllPokemonsResponse, pokemonList) = responseToPokemonList(params.key)
            callback.onResult(
                pokemonList,
                if (response.previous == null) null else params.key - 1
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        scope.launch {
            val (response: AllPokemonsResponse, pokemonList) = responseToPokemonList(params.key)
            callback.onResult(
                pokemonList,
                if (response.next == null) null else (params.key + 1)
            )
        }
    }

    private suspend fun responseToPokemonList(offset: Int): Pair<AllPokemonsResponse, List<Pokemon>> {
        val response: AllPokemonsResponse = apiService.getPagedPokemons(offset)

        val pokemonList =
            response.results.map {
                val id = Util.getId(it.url)

                val fav =
                    AppDatabase.getDatabase(application).PokemonDao().isPokemonFavourite(id)

                Pokemon(id, it.name, it.url, fav ?: false)
            }.toList()
        return Pair(response, pokemonList)
    }


}