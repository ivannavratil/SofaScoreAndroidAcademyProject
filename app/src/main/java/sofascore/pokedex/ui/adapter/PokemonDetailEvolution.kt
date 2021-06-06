package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.PokemonDetailEvolutionRecyclerItemBinding
import sofascore.pokedex.model.TypeDetailMoveResponse


class PokemonDetailEvolution(
    var data: List<TypeDetailMoveResponse>,
    private val context: Context,
) : RecyclerView.Adapter<PokemonDetailEvolution.PokemonDetailEvolutionHolder>() {

    

    class PokemonDetailEvolutionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonDetailEvolutionRecyclerItemBinding =
            PokemonDetailEvolutionRecyclerItemBinding.bind(view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonDetailEvolution.PokemonDetailEvolutionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_detail_evolution_recycler_item, parent, false)
        return PokemonDetailEvolutionHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokemonDetailEvolution.PokemonDetailEvolutionHolder,
        position: Int
    ) {
        val move = data[position];

    
        
    }

    override fun getItemCount(): Int {
        return data.size
    }




}
