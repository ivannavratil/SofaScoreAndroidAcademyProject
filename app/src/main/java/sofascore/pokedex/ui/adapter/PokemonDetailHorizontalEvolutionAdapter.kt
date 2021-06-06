package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.databinding.PokemonDetailEvolutionHorizontalRecyclerItemBinding
import sofascore.pokedex.model.Evolution
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.other.Util.capitalize
import java.util.*


class PokemonDetailHorizontalEvolutionAdapter(
    var data: List<Evolution>,
    private val context: Context,
) : RecyclerView.Adapter<PokemonDetailHorizontalEvolutionAdapter.PokemonDetailEvolutionHolder>() {


    class PokemonDetailEvolutionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonDetailEvolutionHorizontalRecyclerItemBinding =
            PokemonDetailEvolutionHorizontalRecyclerItemBinding.bind(view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonDetailEvolutionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_detail_evolution_horizontal_recycler_item, parent, false)
        return PokemonDetailEvolutionHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokemonDetailEvolutionHolder,
        position: Int
    ) {
        val evolutionPokemon = data[position];

        holder.binding.evolutionCard.cardElevation = 0F

        val b = evolutionPokemon.minLevel == null
        holder.binding.level.text = if (b) "" else "Lv " + evolutionPokemon.minLevel.toString()
        holder.binding.level.visibility = if (b) View.GONE else View.VISIBLE
        holder.binding.arrowAndNum.visibility = if (position == 0) View.GONE else View.VISIBLE


        holder.binding.image.load(Pokemon.getAvatarUrl(evolutionPokemon.id))
        holder.binding.name.text = evolutionPokemon.name.capitalize()
        holder.binding.evolutionLabel.text =
            (position + 1).toString() + context.getString(R.string.evolution).toUpperCase(
                Locale.getDefault()
            )


        val typeRecycler = holder.binding.pokemonDetailEvolutionHorizontalTypeRecyclerItem
        typeRecycler.layoutManager = GridLayoutManager(context, if (data.size == 1) 1 else 2)

        val evolutionAdapter = PokemonDetailEvolutionTypeAdapter(evolutionPokemon.types, context)
        typeRecycler.adapter = evolutionAdapter


    }

    override fun getItemCount(): Int {
        return data.size
    }


}
