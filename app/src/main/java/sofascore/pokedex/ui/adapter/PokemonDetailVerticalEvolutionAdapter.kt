package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.PokemonDetailEvolutionVerticalRecyclerItemBinding
import sofascore.pokedex.model.Evolution


class PokemonDetailVerticalEvolutionAdapter(
    var data: List<List<Evolution>>,
    private val context: Context,
) : RecyclerView.Adapter<PokemonDetailVerticalEvolutionAdapter.PokemonDetailEvolutionHolder>() {


    class PokemonDetailEvolutionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonDetailEvolutionVerticalRecyclerItemBinding =
            PokemonDetailEvolutionVerticalRecyclerItemBinding.bind(view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonDetailEvolutionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_detail_evolution_vertical_recycler_item, parent, false)
        return PokemonDetailEvolutionHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokemonDetailEvolutionHolder,
        position: Int
    ) {
        val evolution = data[position];

        val holderRecycler = holder.binding.verticalRecyclerItem
        holderRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val evolutionAdapter = PokemonDetailHorizontalEvolutionAdapter(evolution, context)
        holderRecycler.adapter = evolutionAdapter

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
