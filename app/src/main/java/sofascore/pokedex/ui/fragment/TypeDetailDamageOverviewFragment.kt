package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import sofascore.pokedex.R
import sofascore.pokedex.databinding.TypeDetailDamageOverviewFragmentBinding
import sofascore.pokedex.ui.adapter.DamageAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel

class TypeDetailDamageOverviewFragment : Fragment() {

    private val typeDetailViewModel: TypeDetailViewModel by activityViewModels()

    private lateinit var binding: TypeDetailDamageOverviewFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = TypeDetailDamageOverviewFragmentBinding.inflate(inflater, container, false)

        val cols = 4


        binding.offensive.title.text = getString(R.string.offensive)
        binding.offensive.rows.twox.recycler.layoutManager =
            StaggeredGridLayoutManager(cols, StaggeredGridLayoutManager.VERTICAL);
        binding.offensive.rows.onehalfx.recycler.layoutManager =
            StaggeredGridLayoutManager(cols, StaggeredGridLayoutManager.VERTICAL);
        binding.offensive.rows.zerox.recycler.layoutManager = GridLayoutManager(context, cols);

        binding.defensive.title.text = getString(R.string.defensive)
        binding.defensive.rows.twox.recycler.layoutManager = GridLayoutManager(context, cols);
        binding.defensive.rows.onehalfx.recycler.layoutManager = GridLayoutManager(context, cols);
        binding.defensive.rows.zerox.recycler.layoutManager = GridLayoutManager(context, cols);

        binding.offensive.rows.twox.times.text = getString(R.string.twox)
        binding.offensive.rows.onehalfx.times.text = getString(R.string.onehalfx)
        binding.offensive.rows.zerox.times.text = getString(R.string.zerox)
        binding.defensive.rows.twox.times.text = getString(R.string.twox)
        binding.defensive.rows.onehalfx.times.text = getString(R.string.onehalfx)
        binding.defensive.rows.zerox.times.text = getString(R.string.zerox)


        binding.offensive.rows.twox.topCard.setCardBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.success,
                null
            )
        )
        binding.offensive.rows.twox.topCard.background.alpha = 25
        //ResourcesCompat.getDrawable(resources,R.color.success,null)


        binding.offensive.rows.twox.times.setTextColor(resources.getColor(R.color.success))
        binding.offensive.rows.onehalfx.times.setTextColor(resources.getColor(R.color.error))
        binding.offensive.rows.zerox.times.setTextColor(resources.getColor(R.color.cold_gray))

        binding.defensive.rows.twox.times.setTextColor(resources.getColor(R.color.success))
        binding.defensive.rows.onehalfx.times.setTextColor(resources.getColor(R.color.error))
        binding.defensive.rows.zerox.times.setTextColor(resources.getColor(R.color.cold_gray))






        typeDetailViewModel.typeDetail.observe(viewLifecycleOwner) {
            binding.offensive.rows.twox.recycler.adapter =
                DamageAdapter(it.damageRelations.doubleDamageTo, requireContext())
            binding.offensive.rows.onehalfx.recycler.adapter =
                DamageAdapter(it.damageRelations.halfDamageTo, requireContext())
            binding.offensive.rows.zerox.recycler.adapter =
                DamageAdapter(it.damageRelations.noDamageTo, requireContext())
            binding.defensive.rows.twox.recycler.adapter =
                DamageAdapter(it.damageRelations.doubleDamageFrom, requireContext())
            binding.defensive.rows.onehalfx.recycler.adapter =
                DamageAdapter(it.damageRelations.halfDamageFrom, requireContext())
            binding.defensive.rows.zerox.recycler.adapter =
                DamageAdapter(it.damageRelations.noDamageFrom, requireContext())
        }


        return binding.root
    }


}