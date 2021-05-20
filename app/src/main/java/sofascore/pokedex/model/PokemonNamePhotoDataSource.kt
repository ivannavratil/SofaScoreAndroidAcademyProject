package sofascore.pokedex.model

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonNamePhotoDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, PokemonNamePhoto>() {

    private val apiService = Network().service

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PokemonNamePhoto>) {
        scope.launch {
            val response = apiService.getPagedPokemons(0)
            callback.onResult(response.body()!!.results, null, 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonNamePhoto>) {
        //TODO: fix params
        scope.launch {
            val response = apiService.getPagedPokemons(params.key)
            callback.onResult(response.body()!!.results, params.key-1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonNamePhoto>) {
        //TODO: fix params
        scope.launch {
            val response = apiService.getPagedPokemons(params.key)
            callback.onResult(response.body()!!.results, params.key+1)
        }
    }


}