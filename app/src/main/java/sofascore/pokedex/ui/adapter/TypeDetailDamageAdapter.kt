package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.databinding.TypeDetailDamageRecyclerItemBinding
import sofascore.pokedex.model.TypeDetailResponse.DamageRelations.Damage
import sofascore.pokedex.other.Util
import sofascore.pokedex.other.Util.capitalize
import sofascore.pokedex.ui.activity.TypeDetailActivity
import sofascore.pokedex.ui.fragment.TypeDetailDamageOverviewFragment.Power
import java.util.*


class TypeDetailDamageAdapter(
    private var data: List<Damage>,
    private val context: Context,
    private val power: Power,
) : RecyclerView.Adapter<TypeDetailDamageAdapter.DamageHolder>() {

    init {
        if (data.isEmpty()) {
            data = arrayListOf(Damage(context.getString(R.string.none), ""))
        }
    }

    inner class DamageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: TypeDetailDamageRecyclerItemBinding =
            TypeDetailDamageRecyclerItemBinding.bind(view)

        init {
            view.setOnClickListener {
                if (data[adapterPosition].name != context.resources.getString(R.string.none)) {
                    onClick(Util.getId(data[adapterPosition].url), view.context)
                }
            }
        }

        private fun onClick(id: Int, context: Context) {
            val intent = Intent(context, TypeDetailActivity()::class.java)
            intent.putExtra(TypeDetailActivity.type, id)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeDetailDamageAdapter.DamageHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_detail_type_recycler_item, parent, false)
        return DamageHolder(view)
    }

    override fun onBindViewHolder(
        holder: TypeDetailDamageAdapter.DamageHolder,
        position: Int
    ) {
        val type = data[position];

        val textView: AppCompatButton = holder.binding.type;
        textView.text = type.name.capitalize()

        if (type.name == context.getString(R.string.none)) {

            holder.binding.type.setTextAppearance(R.style.Headline3ColdGrayLeft)

            holder.binding.type.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT);


            when (power) {
                Power.ZEROX -> {
                    holder.binding.type.setTextColor(
                        context.resources.getColor(
                            R.color.cold_gray,
                            null
                        )
                    )
                }
                Power.HALFX -> holder.binding.type.setTextColor(
                    context.resources.getColor(
                        R.color.error,
                        null
                    )
                )
                Power.TWOX -> holder.binding.type.setTextColor(
                    context.resources.getColor(
                        R.color.success,
                        null
                    )
                )
            }


        } else {

            val identifier = context.resources.getIdentifier(
                "flat_pokemon_type_" + type.name.lowercase(Locale.getDefault()),
                "color",
                context.packageName
            )

            holder.binding.type.backgroundTintList =
                ColorStateList.valueOf(context.resources.getColor(identifier));

        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}
