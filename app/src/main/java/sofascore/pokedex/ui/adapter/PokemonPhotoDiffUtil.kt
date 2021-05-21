package sofascore.pokedex.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import sofascore.pokedex.model.PokemonNamePhoto

class PokemonPhotoDiffUtil: DiffUtil.ItemCallback<PokemonNamePhoto>() {
    override fun areItemsTheSame(oldItem: PokemonNamePhoto, newItem: PokemonNamePhoto): Boolean {
        return oldItem.url == newItem.url;
    }

    override fun areContentsTheSame(oldItem: PokemonNamePhoto, newItem: PokemonNamePhoto): Boolean {
       return oldItem.name == newItem.name && oldItem.url == newItem.url;
    }
}