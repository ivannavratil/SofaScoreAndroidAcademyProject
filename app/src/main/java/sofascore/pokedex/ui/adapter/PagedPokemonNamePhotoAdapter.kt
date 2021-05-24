package sofascore.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.Util
import sofascore.pokedex.databinding.FragmentSearchRecyclerItemBinding
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.ui.adapter.PagedPokemonNamePhotoAdapter.*
import java.util.*

class PagedPokemonNamePhotoAdapter :
    PagedListAdapter<Pokemon, PokemonViewHolder>(PokemonPhotoDiffUtil()) {


    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentSearchRecyclerItemBinding.bind(view)

        fun bindPokemons(pokemon: Pokemon) {
            binding.pokemonName.text = pokemon.name.capitalize(Locale.getDefault())

           val id = Util.getId(pokemon.url)

            binding.pokemonNum.text = "0".repeat(3-id.toString().length)+id;

            binding.pokemonFav.setImageResource(
                when (pokemon.favourite) {
                    true -> R.drawable.ic_star_1
                    false -> R.drawable.ic_star_0
                }
            )


            //TODO: move to somewhere else
            binding.pokemonPhoto.load(("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+id+".png"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_search_recycler_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindPokemons(it);
        }
    }


}