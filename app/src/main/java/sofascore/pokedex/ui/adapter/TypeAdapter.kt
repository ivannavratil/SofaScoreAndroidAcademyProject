package sofascore.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.Util.capitalize
import sofascore.pokedex.databinding.PokemonTypeRecyclerItemBinding
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.ui.fragment.PlaceholderFragment
import java.util.*


class TypeAdapter(
    private val data: List<DetailPokemonResponse.Type>,
    private val context: Context,
) : RecyclerView.Adapter<TypeAdapter.TypeHolder>() {

    inner class TypeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonTypeRecyclerItemBinding = PokemonTypeRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onClick(data[adapterPosition], view.context)
            }
        }

        private fun onClick(type: DetailPokemonResponse.Type, context: Context) {

            //TODO: fix

            val nextFrag = PlaceholderFragment()

            (context as AppCompatActivity)
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, nextFrag)
                .commitNow()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.TypeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_type_recycler_item, parent, false)
        return TypeHolder(view)
    }

    override fun onBindViewHolder(
        holder: TypeHolder,
        position: Int
    ) {
        val type = data[position];

        val button: Button = holder.binding.type;
        button.text = type.type.name.capitalize()

        val identifier = context.resources.getIdentifier(
            "flat_pokemon_type_" + type.type.name.lowercase(Locale.getDefault()),
            "color",
            context.packageName
        )

        button.setBackgroundColor(identifier)

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
