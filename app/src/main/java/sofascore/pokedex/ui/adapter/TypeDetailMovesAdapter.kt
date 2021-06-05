package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.TypeDetailMovesRecyclerItemBinding
import sofascore.pokedex.model.TypeDetailMoveResponse
import sofascore.pokedex.other.Util
import sofascore.pokedex.other.Util.capitalize


class TypeDetailMovesAdapter(
    private val data: List<TypeDetailMoveResponse>,
    private val context: Context,
) : RecyclerView.Adapter<TypeDetailMovesAdapter.TypeDetailPokemonHolder>() {

    inner class TypeDetailPokemonHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: TypeDetailMovesRecyclerItemBinding =
            TypeDetailMovesRecyclerItemBinding.bind(view)

//        init {
//            view.setOnClickListener {
//                onClick(data[adapterPosition], view.context)
//            }
//        }
//
//        private fun onClick(type: TypeDetailMoveResponse, context: Context) {
//
//            val intent = Intent(context, DetailPokemonActivity()::class.java)
//
//            //intent.putExtra(DetailPokemonActivity.pokemonById, Util.getId(type.pokemon.url))
//            context.startActivity(intent)
//        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeDetailMovesAdapter.TypeDetailPokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_detail_moves_recycler_item, parent, false)
        return TypeDetailPokemonHolder(view)
    }

    override fun onBindViewHolder(
        holder: TypeDetailMovesAdapter.TypeDetailPokemonHolder,
        position: Int
    ) {
        val move = data[position];

        holder.binding.gen.text = move.generation.name.split("-")[1].trim().toUpperCase()


        holder.binding.gen.setBackgroundColor(
            context.resources.getColor(
                when (holder.binding.gen.text) {
                    "I" -> R.color.flat_pokemon_type_grass
                    "II" -> R.color.flat_pokemon_type_bug
                    "III" -> R.color.flat_pokemon_type_undefined
                    "IV" -> R.color.flat_pokemon_type_ghost
                    "V" -> R.color.flat_pokemon_type_water
                    "VI" -> R.color.flat_pokemon_type_fighting
                    "VII" -> R.color.flat_pokemon_type_fire
                    "VIII" -> R.color.flat_pokemon_type_poison
                    else -> R.color.surface_1
                }, null
            )
        )
        holder.binding.gen.background.alpha = 76

        holder.binding.move.text = Util.replaceMinusWithSpaceAndUppercase(move.name)


        holder.binding.category.text =
            if (move.damageClass != null) move.damageClass.name.capitalize() else ""
        holder.binding.category.setBackgroundColor(
            context.resources.getColor(
                when (holder.binding.category.text) {
                    "Physical" -> R.color.error
                    "Special" -> R.color.cold_gray
                    else -> R.color.surface_1
                }, null
            )
        )
        holder.binding.category.background.alpha = 76

        holder.binding.power.text = move.power.toString()
        holder.binding.pp.text = move.pp.toString()


    }

    override fun getItemCount(): Int {
        return data.size
    }


}
