package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.Util
import sofascore.pokedex.databinding.TypeDetailPokemonRecyclerItemBinding
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.model.TypeDetailResponse
import sofascore.pokedex.ui.activity.DetailPokemonActivity


class TypeDetailPokemonAdapter(
    private val data: List<TypeDetailResponse.Pokemon>,
    private val context: Context,
) : RecyclerView.Adapter<TypeDetailPokemonAdapter.TypeDetailPokemonHolder>() {

    inner class TypeDetailPokemonHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: TypeDetailPokemonRecyclerItemBinding =
            TypeDetailPokemonRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onClick(data[adapterPosition], view.context)
            }
        }

        private fun onClick(type: TypeDetailResponse.Pokemon, context: Context) {

            val intent = Intent(context, DetailPokemonActivity()::class.java)

            intent.putExtra(DetailPokemonActivity.pokemonById, Util.getId(type.pokemon.url))
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeDetailPokemonAdapter.TypeDetailPokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_detail_pokemon_recycler_item, parent, false)
        return TypeDetailPokemonHolder(view)
    }

    override fun onBindViewHolder(
        holder: TypeDetailPokemonAdapter.TypeDetailPokemonHolder,
        position: Int
    ) {
        val type = data[position];

        holder.binding.pokemonName.text = type.pokemon.name
        holder.binding.pokemonPhoto.load(Pokemon.getAvatarUrl(Util.getId(type.pokemon.url)))

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
