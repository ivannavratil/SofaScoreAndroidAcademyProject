package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.Util
import sofascore.pokedex.Util.capitalize
import sofascore.pokedex.databinding.TypeDetailRecyclerItemBinding
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.ui.activity.TypeDetailActivity
import java.util.*


class TypeAdapter(
    private val data: List<DetailPokemonResponse.Type>,
    private val context: Context,
) : RecyclerView.Adapter<TypeAdapter.TypeHolder>() {

    inner class TypeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: TypeDetailRecyclerItemBinding = TypeDetailRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onClick(data[adapterPosition], view.context)
            }
        }

        private fun onClick(type: DetailPokemonResponse.Type, context: Context) {

            val intent = Intent(context, TypeDetailActivity()::class.java)

            intent.putExtra(TypeDetailActivity.type, Util.getId(type.type.url))
            context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.TypeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.type_detail_recycler_item, parent, false)
        return TypeHolder(view)
    }

    override fun onBindViewHolder(
        holder: TypeAdapter.TypeHolder,
        position: Int
    ) {
        val type = data[position];

        val textView: AppCompatButton = holder.binding.type;
        textView.text = type.type.name.capitalize()

        val identifier = context.resources.getIdentifier(
            "flat_pokemon_type_" + type.type.name.lowercase(Locale.getDefault()),
            "color",
            context.packageName
        )

        holder.binding.type.backgroundTintList =
            ColorStateList.valueOf(context.resources.getColor(identifier));

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
