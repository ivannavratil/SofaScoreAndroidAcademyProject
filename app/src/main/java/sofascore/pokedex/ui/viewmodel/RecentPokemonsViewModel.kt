package sofascore.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import sofascore.pokedex.model.PokemonNamePhoto
import sofascore.pokedex.model.PokemonNamePhotoDataSource

class RecentPokemonsViewModel : ViewModel() {
    var pagingPokemonsList: LiveData<PagedList<PokemonNamePhoto>>

    init {
        val config = PagedList.Config.Builder().setPageSize(40).setInitialLoadSizeHint(50).setEnablePlaceholders(false).build()
        pagingPokemonsList = initializePagedList(config).build()
    }

    private fun initializePagedList(config: PagedList.Config): LivePagedListBuilder<Int, PokemonNamePhoto> {
        val dataSourceFactory = object : DataSource.Factory<Int, PokemonNamePhoto>() {
            override fun create(): DataSource<Int, PokemonNamePhoto> {
                return PokemonNamePhotoDataSource(viewModelScope)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}