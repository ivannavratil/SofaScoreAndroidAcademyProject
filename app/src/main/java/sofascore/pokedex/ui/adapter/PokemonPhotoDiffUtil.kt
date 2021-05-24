package sofascore.pokedex.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.model.PokemonNamePhoto

class PokemonPhotoDiffUtil: DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id;
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
       return oldItem == newItem
    }
}