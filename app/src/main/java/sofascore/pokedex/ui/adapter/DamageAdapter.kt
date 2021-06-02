package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.Util.capitalize
import sofascore.pokedex.databinding.PokemonDamageRecyclerItemBinding
import sofascore.pokedex.model.TypeDetailResponse.DamageRelations.Damage
import java.util.*


class DamageAdapter(
    private val data: List<Damage>,
    private val context: Context,
) : RecyclerView.Adapter<DamageAdapter.DamageHolder>() {

    inner class DamageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonDamageRecyclerItemBinding = PokemonDamageRecyclerItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DamageAdapter.DamageHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_type_recycler_item, parent, false)
        return DamageHolder(view)
    }

    override fun onBindViewHolder(
        holder: DamageAdapter.DamageHolder,
        position: Int
    ) {
        val type = data[position];

        val textView: AppCompatButton = holder.binding.type;
        textView.text = type.name.capitalize()

        val identifier = context.resources.getIdentifier(
            "flat_pokemon_type_" + type.name.lowercase(Locale.getDefault()),
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
