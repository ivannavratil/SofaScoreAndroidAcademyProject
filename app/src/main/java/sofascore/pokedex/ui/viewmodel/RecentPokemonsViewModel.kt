package sofascore.pokedex.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.model.PokemonDataSource

class RecentPokemonsViewModel(application: Application) : AndroidViewModel(application) {
    var pagingPokemonsList: LiveData<PagedList<Pokemon>>


    init {
        val config = PagedList.Config.Builder().setPageSize(40).setInitialLoadSizeHint(50)
            .setEnablePlaceholders(false).build()
        pagingPokemonsList = initializePagedList(config).build()
    }

    private fun initializePagedList(config: PagedList.Config): LivePagedListBuilder<Int, Pokemon> {
        val dataSourceFactory = object : DataSource.Factory<Int, Pokemon>() {
            override fun create(): DataSource<Int, Pokemon> {
                return PokemonDataSource(
                    viewModelScope,
                    this@RecentPokemonsViewModel.getApplication()
                )
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

}