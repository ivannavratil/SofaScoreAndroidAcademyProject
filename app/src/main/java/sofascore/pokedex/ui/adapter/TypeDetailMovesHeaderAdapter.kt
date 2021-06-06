package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.TypeDetailMovesRecyclerItemHeaderBinding
import java.util.*


class TypeDetailMovesHeaderAdapter(
    private val movesAdapter: TypeDetailMovesAdapter,
    private val context: Context,
) : RecyclerView.Adapter<TypeDetailMovesHeaderAdapter.TypeDetailPokemonHeaderHolder>() {

    inner class TypeDetailPokemonHeaderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: TypeDetailMovesRecyclerItemHeaderBinding =
            TypeDetailMovesRecyclerItemHeaderBinding.bind(view)

        init {
            binding.gen.setOnClickListener { movesAdapter.sortBy(0) }
            binding.move.setOnClickListener { movesAdapter.sortBy(1) }
            binding.category.setOnClickListener { movesAdapter.sortBy(2) }
            binding.power.setOnClickListener { movesAdapter.sortBy(3) }
            binding.pp.setOnClickListener { movesAdapter.sortBy(4) }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeDetailMovesHeaderAdapter.TypeDetailPokemonHeaderHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_detail_moves_recycler_item_header, parent, false)
        return TypeDetailPokemonHeaderHolder(view)
    }

    override fun onBindViewHolder(
        holder: TypeDetailMovesHeaderAdapter.TypeDetailPokemonHeaderHolder,
        position: Int
    ) {

        holder.binding.gen.text = context.getString(R.string.gen).uppercase(Locale.getDefault())

        holder.binding.move.text = context.getString(R.string.move).uppercase(Locale.getDefault())

        holder.binding.category.text =
            context.getString(R.string.category).uppercase(Locale.getDefault())

        holder.binding.power.text = context.getString(R.string.power).uppercase(Locale.getDefault())

        holder.binding.pp.text = context.getString(R.string.pp).uppercase(Locale.getDefault())

    }

    override fun getItemCount(): Int {
        return 1
    }
}
