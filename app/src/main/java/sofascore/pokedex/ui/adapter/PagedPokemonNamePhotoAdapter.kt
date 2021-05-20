package sofascore.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.play.core.internal.bi
import sofascore.pokedex.R
import sofascore.pokedex.databinding.PokemonCardSearchBinding
import sofascore.pokedex.model.PokemonNamePhoto

class PagedPokemonNamePhotoAdapter :
    PagedListAdapter<PokemonNamePhoto, PagedPokemonNamePhotoAdapter.PokemonViewHolder>(PokemonPhotoDiffUtil()) {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PokemonCardSearchBinding.bind(view)

        fun bindPokemons(pokemon: PokemonNamePhoto) {
            binding.pokemonName.text = pokemon.name
            binding.pokemonPhoto.load(pokemon.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_card_search, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindPokemons(it);
        }
    }


}