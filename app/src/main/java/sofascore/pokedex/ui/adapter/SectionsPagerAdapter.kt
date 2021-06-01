package sofascore.pokedex.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import sofascore.pokedex.R
import sofascore.pokedex.ui.fragment.TypeDetailDamageOverviewFragment
import sofascore.pokedex.ui.fragment.TypeDetailMovesFragment
import sofascore.pokedex.ui.fragment.TypeDetailPokemonsFragment


private val TAB_TITLES = arrayOf(
    R.string.DAMAGE_OVERVIEW,
    R.string.MOVES,
    R.string.POKEMONS
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TypeDetailDamageOverviewFragment()
            1 -> TypeDetailMovesFragment()
            2 -> TypeDetailPokemonsFragment()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}