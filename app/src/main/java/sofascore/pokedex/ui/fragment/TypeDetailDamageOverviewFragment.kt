package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import sofascore.pokedex.R
import sofascore.pokedex.other.Util
import sofascore.pokedex.databinding.TypeDetailDamageOverviewFragmentBinding
import sofascore.pokedex.ui.adapter.TypeDetailDamageAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel

class TypeDetailDamageOverviewFragment : Fragment() {

    private val typeDetailViewModel: TypeDetailViewModel by activityViewModels()

    private lateinit var binding: TypeDetailDamageOverviewFragmentBinding

    private val itemTypeWidth = 85.0;
    private val nonRecyclerWidth = 86
    private val cardBackgroundOpacity = 26


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = TypeDetailDamageOverviewFragmentBinding.inflate(inflater, container, false)

        val cols = 4


        binding.offensive.title.text = getString(R.string.offensive)
        binding.offensive.rows.twox.recycler.layoutManager = getRecyclerLayout()
        binding.offensive.rows.onehalfx.recycler.layoutManager = getRecyclerLayout()
        binding.offensive.rows.zerox.recycler.layoutManager = getRecyclerLayout()

        binding.defensive.title.text = getString(R.string.defensive)
        binding.defensive.rows.twox.recycler.layoutManager = getRecyclerLayout()
        binding.defensive.rows.onehalfx.recycler.layoutManager = getRecyclerLayout()
        binding.defensive.rows.zerox.recycler.layoutManager = getRecyclerLayout()

        binding.offensive.rows.twox.times.text = getString(R.string.twox)
        binding.offensive.rows.onehalfx.times.text = getString(R.string.onehalfx)
        binding.offensive.rows.zerox.times.text = getString(R.string.zerox)
        binding.defensive.rows.twox.times.text = getString(R.string.twox)
        binding.defensive.rows.onehalfx.times.text = getString(R.string.onehalfx)
        binding.defensive.rows.zerox.times.text = getString(R.string.zerox)


        binding.offensive.rows.twox.topCard.setCardBackgroundColor(getColor(R.color.success))
        binding.offensive.rows.twox.topCard.background.alpha = cardBackgroundOpacity
        binding.offensive.rows.onehalfx.topCard.setCardBackgroundColor(getColor(R.color.error))
        binding.offensive.rows.onehalfx.topCard.background.alpha = cardBackgroundOpacity
        binding.offensive.rows.zerox.topCard.setCardBackgroundColor(getColor(R.color.surface_2))

        binding.defensive.rows.twox.topCard.setCardBackgroundColor(getColor(R.color.success))
        binding.defensive.rows.twox.topCard.background.alpha = cardBackgroundOpacity
        binding.defensive.rows.onehalfx.topCard.setCardBackgroundColor(getColor(R.color.error))
        binding.defensive.rows.onehalfx.topCard.background.alpha = cardBackgroundOpacity
        binding.defensive.rows.zerox.topCard.setCardBackgroundColor(getColor(R.color.surface_2))


        binding.offensive.rows.twox.times.setTextColor(resources.getColor(R.color.success, null))
        binding.offensive.rows.onehalfx.times.setTextColor(resources.getColor(R.color.error, null))
        binding.offensive.rows.zerox.times.setTextColor(resources.getColor(R.color.cold_gray, null))

        binding.defensive.rows.twox.times.setTextColor(resources.getColor(R.color.success, null))
        binding.defensive.rows.onehalfx.times.setTextColor(resources.getColor(R.color.error, null))
        binding.defensive.rows.zerox.times.setTextColor(resources.getColor(R.color.cold_gray, null))


        typeDetailViewModel.typeDetail.observe(viewLifecycleOwner) {
            binding.offensive.rows.twox.recycler.adapter =
                TypeDetailDamageAdapter(it.damageRelations.doubleDamageTo, requireContext(), Power.TWOX)
            binding.offensive.rows.onehalfx.recycler.adapter =
                TypeDetailDamageAdapter(it.damageRelations.halfDamageTo, requireContext(), Power.HALFX)
            binding.offensive.rows.zerox.recycler.adapter =
                TypeDetailDamageAdapter(it.damageRelations.noDamageTo, requireContext(), Power.ZEROX)
            binding.defensive.rows.twox.recycler.adapter =
                TypeDetailDamageAdapter(it.damageRelations.doubleDamageFrom, requireContext(), Power.TWOX)
            binding.defensive.rows.onehalfx.recycler.adapter =
                TypeDetailDamageAdapter(it.damageRelations.halfDamageFrom, requireContext(), Power.HALFX)
            binding.defensive.rows.zerox.recycler.adapter =
                TypeDetailDamageAdapter(it.damageRelations.noDamageFrom, requireContext(), Power.ZEROX)
        }

        return binding.root
    }

    private fun getRecyclerLayout(): GridLayoutManager {
        return GridLayoutManager(
            requireContext(),
            Util.calculateNoOfColumns(requireContext(), itemTypeWidth, nonRecyclerWidth)
        )
    }

    private fun getColor(colorId: Int): Int {
        return ResourcesCompat.getColor(
            resources,
            colorId,
            null
        )
    }

    enum class Power {
        TWOX, HALFX, ZEROX
    }


}