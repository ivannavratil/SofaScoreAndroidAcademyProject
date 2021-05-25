package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.databinding.FragmentSearchRecyclerItemBinding
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.ui.viewmodel.FavoriteViewModel
import java.util.*
import kotlin.collections.ArrayList

class FavoritePokemonsAdapter(
    private val data: ArrayList<Pokemon>,
    private val context: Context,
    private val favoriteViewModel: FavoriteViewModel
) : RecyclerView.Adapter<FavoritePokemonsAdapter.PokemonHolder>() {

    private var handlerVisible: Boolean = false

    fun changeHandlerVisibility(): Boolean {
        handlerVisible = !handlerVisible
        this.notifyDataSetChanged()
        return handlerVisible
    }

    inner class PokemonHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = FragmentSearchRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onClick(data[adapterPosition], view.context)
            }
        }

        private fun onClick(pokemon: Pokemon, context: Context) {
//            val intent = Intent(context, WeatherCityDetailActivity()::class.java)
//
//            intent.putExtra(WeatherCityDetailActivity.WEATHER_CITY_BY_ID, weatherCity.woeID)
//            context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_search_recycler_item, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokemonHolder,
        position: Int
    ) {

        holder.binding.handler.visibility = if (handlerVisible) View.VISIBLE
        else View.GONE

        val pokemon = data[position]

        holder.binding.pokemonPhoto.load(pokemon.getAvatarUrl())
        holder.binding.pokemonName.text = pokemon.name.capitalize(Locale.getDefault())
        holder.binding.pokemonNum.text = pokemon.getFormattedId()

        holder.binding.pokemonFav.setImageResource(
            when (pokemon.favourite) {
                true -> R.drawable.ic_star_1
                false -> R.drawable.ic_star_0
            }
        )


        holder.binding.pokemonFav.setOnClickListener { v ->
            if (v is ImageView && !handlerVisible) {
                val newFavourite = !pokemon.favourite

                favoriteViewModel.flipFavourite(context, pokemon)

                v.setImageResource(if (newFavourite) R.drawable.ic_star_1 else R.drawable.ic_star_0)

                data.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, data.size)
            }
        }



    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): Pokemon {
        return data[position]
    }



}
