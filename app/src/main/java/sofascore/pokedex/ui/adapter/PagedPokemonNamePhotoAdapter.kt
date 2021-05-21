package sofascore.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.databinding.FragmentSearchRecyclerItemBinding
import sofascore.pokedex.model.PokemonNamePhoto
import sofascore.pokedex.ui.adapter.PagedPokemonNamePhotoAdapter.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class PagedPokemonNamePhotoAdapter :
    PagedListAdapter<PokemonNamePhoto, PokemonViewHolder>(PokemonPhotoDiffUtil()) {

    companion object {
        private val pattern: Pattern = Pattern.compile("/\\d+/")
    }

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentSearchRecyclerItemBinding.bind(view)

        fun bindPokemons(pokemon: PokemonNamePhoto) {
            binding.pokemonName.text = pokemon.name.capitalize(Locale.getDefault())

            val matcher: Matcher = pattern.matcher(pokemon.url)
            matcher.find()
            val idWithSlashes = matcher.group(0)!!
            val id = idWithSlashes.substring(1, idWithSlashes.length - 1);

            binding.pokemonNum.text = "0".repeat(3-id.length)+id;

            //TODO: move to somewhere else
            binding.pokemonPhoto.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png")
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