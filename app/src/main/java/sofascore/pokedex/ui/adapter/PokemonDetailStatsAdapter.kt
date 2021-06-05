package sofascore.pokedex.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.R
import sofascore.pokedex.other.Util.capitalize
import sofascore.pokedex.databinding.PokemonDetailStatsRecyclerItemBinding
import sofascore.pokedex.model.db.DetailPokemonResponse.Stat

class PokemonDetailStatsAdapter(private val data: List<Stat>, private val context: Context) :
    RecyclerView.Adapter<PokemonDetailStatsAdapter.StatsHolder>() {


    inner class StatsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: PokemonDetailStatsRecyclerItemBinding =
            PokemonDetailStatsRecyclerItemBinding.bind(view)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_detail_stats_recycler_item, parent, false)
        return StatsHolder(view)
    }

    override fun onBindViewHolder(holder: StatsHolder, position: Int) {

        val stat = data[position]

        holder.binding.statsKey.text = adjustShowName(stat.stat.name)
        holder.binding.statsValue.text = stat.baseStat.toString()
        holder.binding.progressBar.progress = stat.baseStat





        holder.binding.progressBar.progressTintList = ColorStateList.valueOf(
            context.resources.getColor(
                when (position) {
                    0 -> R.color.flat_base_stats_01_hp
                    1 -> R.color.flat_base_stats_02_attack
                    2 -> R.color.flat_base_stats_03_defense
                    3 -> R.color.flat_base_stats_04_sp_atk
                    4 -> R.color.flat_base_stats_05_sp_def
                    5 -> R.color.flat_base_stats_06_speed
                    else -> R.color.dark
                }
            )
        );


    }

    private fun adjustShowName(name: String): String {
        val split = name.split("-");
        return if (split.size == 1) {
            name.capitalize()
        } else {
            val first = split[0].subSequence(0, 2).toString().capitalize();
            val second = split[1].subSequence(0, 3).toString().capitalize();
            ("$first. $second:")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
