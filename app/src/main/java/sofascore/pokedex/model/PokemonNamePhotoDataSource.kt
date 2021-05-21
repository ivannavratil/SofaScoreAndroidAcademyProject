package sofascore.pokedex.model

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sofascore.pokedex.model.network.Network
import java.util.regex.Matcher
import java.util.regex.Pattern


class PokemonNamePhotoDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, PokemonNamePhoto>() {

    companion object {
        private val pattern: Pattern = Pattern.compile("offset=\\d+")
    }
    private val apiService = Network().service

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonNamePhoto>
    ) {
        scope.launch {
            //TODO: change to return Pokemon instead of PokemonNamePhoto
            val response1 = apiService.getPagedPokemons(0)

            val body = response1.body()!!
            callback.onResult(response1.body()!!.results, null,
                if(body.next==null) null else (extractOffset(body.next) + 1))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonNamePhoto>
    ) {
        scope.launch {
            val response = apiService.getPagedPokemons(params.key)
            val body = response.body()!!
            callback.onResult(
                body.results,
                if (body.previous == null) null else params.key - 1
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonNamePhoto>) {
        scope.launch {
            val response = apiService.getPagedPokemons(params.key)
            val body = response.body()!!
            callback.onResult(
                response.body()!!.results,
                if (body.next == null) null else (params.key + 1)
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