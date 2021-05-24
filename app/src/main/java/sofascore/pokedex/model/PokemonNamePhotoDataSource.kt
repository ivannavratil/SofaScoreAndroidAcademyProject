package sofascore.pokedex.model

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.network.Network
import java.util.regex.Matcher
import java.util.regex.Pattern


class PokemonNamePhotoDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Pokemon>() {

    companion object {
        private val pattern: Pattern = Pattern.compile("offset=\\d+")
    }

    private val apiService = Network().service

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pokemon>
    ) {
        scope.launch {

            val response: AllPokemonsResponse = apiService.getPagedPokemons(0)

            val pokemonList = response.results.map { Pokemon(it, false) }.toList();

            callback.onResult(
                pokemonList, null,
                if (response.next == null) null else (extractOffset(response.next) + 1)
            )
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Pokemon>
    ) {
        scope.launch {
            val response = apiService.getPagedPokemons(params.key)
            val pokemonList = response.results.map { Pokemon(it, false) }.toList();
            callback.onResult(
                pokemonList,
                if (response.previous == null) null else params.key - 1
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pokemon>) {
        scope.launch {
            val response = apiService.getPagedPokemons(params.key)
            val pokemonList = response.results.map { Pokemon(it, false) }.toList();
            callback.onResult(
                pokemonList,
                if (response.next == null) null else (params.key + 1)
            )
        }
    }

    private fun extractOffset(string: String): Int {
        val matcher: Matcher = pattern.matcher(string)
        matcher.find()
        val offsetWithNumber = matcher.group(0)!!
        return Integer.parseInt(offsetWithNumber.substring(offsetWithNumber.indexOf("=") + 1))

    }


}