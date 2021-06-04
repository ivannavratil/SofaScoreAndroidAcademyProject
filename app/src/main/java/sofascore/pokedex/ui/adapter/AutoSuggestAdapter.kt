package sofascore.pokedex.ui.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import sofascore.pokedex.model.PokemonNamePhoto

class AutoSuggestAdapter(
    context: Context,
    resource: Int,
    private val data: List<PokemonNamePhoto>
) : ArrayAdapter<PokemonNamePhoto>(context, resource), Filterable {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): PokemonNamePhoto {
        return data[position]
    }

    override fun getFilter(): Filter {

        return (object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    filterResults.values = data
                    filterResults.count = data.size
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && (results.count > 0)) {
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        })
    }


}
