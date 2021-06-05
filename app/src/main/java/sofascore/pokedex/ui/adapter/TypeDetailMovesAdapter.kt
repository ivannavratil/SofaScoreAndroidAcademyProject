package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.TypeDetailMovesRecyclerItemBinding
import sofascore.pokedex.model.TypeDetailMoveResponse
import sofascore.pokedex.ui.activity.DetailPokemonActivity


class TypeDetailMovesAdapter(
    private val data: List<TypeDetailMoveResponse>,
    private val context: Context,
) : RecyclerView.Adapter<TypeDetailMovesAdapter.TypeDetailPokemonHolder>() {

    inner class TypeDetailPokemonHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: TypeDetailMovesRecyclerItemBinding =
            TypeDetailMovesRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onClick(data[adapterPosition], view.context)
            }
        }

        private fun onClick(type: TypeDetailMoveResponse, context: Context) {

            val intent = Intent(context, DetailPokemonActivity()::class.java)

            //intent.putExtra(DetailPokemonActivity.pokemonById, Util.getId(type.pokemon.url))
            context.startActivity(intent)
        }
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

        holder.binding.gen.text = move.generation.name.split("-")[1].toUpperCase()
        holder.binding.move.text = move.name
        holder.binding.category.text =
            if (move.meta == null || move.meta.category==null) "" else move.meta.category.name
        holder.binding.power.text = move.power.toString()
        holder.binding.pp.text = move.pp.toString()


    }

    override fun getItemCount(): Int {
        return data.size
    }


}
