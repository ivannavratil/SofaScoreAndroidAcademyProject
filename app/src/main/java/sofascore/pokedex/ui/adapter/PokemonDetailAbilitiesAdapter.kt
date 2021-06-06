package sofascore.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.PokemonDetailAbilitiesRecyclerItemBinding
import sofascore.pokedex.model.db.DetailPokemonResponse.Ability
import sofascore.pokedex.other.Util.capitalize

class PokemonDetailAbilitiesAdapter(
    private val data: List<Ability>,
) : RecyclerView.Adapter<PokemonDetailAbilitiesAdapter.AbilityHolder>() {


    inner class AbilityHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonDetailAbilitiesRecyclerItemBinding =
            PokemonDetailAbilitiesRecyclerItemBinding.bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_detail_abilities_recycler_item, parent, false)
        return AbilityHolder(view)
    }

    override fun onBindViewHolder(
        holder: AbilityHolder,
        position: Int
    ) {
        val type = data[position];

        holder.binding.ability.text = type.ability.name.capitalize()
        holder.binding.abilityHidden.visibility =
            if (type.isHidden) View.GONE else View.VISIBLE


    }

    override fun getItemCount(): Int {
        return data.size
    }


}
