package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.databinding.PokemonSearchRecyclerItemBinding
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.other.Util.capitalize
import sofascore.pokedex.ui.activity.DetailPokemonActivity
import sofascore.pokedex.ui.adapter.PokemonPagedAdapter.PokemonViewHolder
import sofascore.pokedex.ui.adapter.helper.PokemonPhotoDiffUtil
import sofascore.pokedex.ui.viewmodel.FavoriteViewModel

class PokemonPagedAdapter(
    private val context: Context,
    private val pagedViewModel: FavoriteViewModel
) :
    PagedListAdapter<Pokemon, PokemonViewHolder>(PokemonPhotoDiffUtil()) {

    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PokemonSearchRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                val parseInt = Integer.parseInt(binding.pokemonNum.text as String)
                onClick(parseInt, view.context)
            }
        }

        private fun onClick(id: Int, context: Context) {
            val intent = Intent(context, DetailPokemonActivity()::class.java)
            intent.putExtra(DetailPokemonActivity.pokemonById, id)
            context.startActivity(intent)
        }

        fun bindPokemons(
            pokemon: Pokemon,
            context: Context,
            pagedViewModel: FavoriteViewModel
        ) {
            binding.pokemonName.text = pokemon.name.capitalize()

            binding.pokemonNum.text = pokemon.getFormattedId()

            binding.pokemonFav.setImageResource(
                when (pokemon.favourite) {
                    true -> R.drawable.ic_star_1
                    false -> R.drawable.ic_star_0
                }
            )

            binding.pokemonFav.setOnClickListener { v ->
                if (v is ImageView) {

                    val newFavourite = !pokemon.favourite

                    pagedViewModel.flipFavourite(context, pokemon)

                    v.setImageResource(if (newFavourite) R.drawable.ic_star_1 else R.drawable.ic_star_0)
                }
            }
            binding.pokemonPhoto.load(pokemon.getAvatarUrl())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_search_recycler_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindPokemons(it, context, pagedViewModel)
        }
    }
}