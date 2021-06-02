package sofascore.pokedex.ui.fragment

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import sofascore.pokedex.R
import sofascore.pokedex.Util
import sofascore.pokedex.databinding.TypeDetailPokemonsFragmentBinding
import sofascore.pokedex.ui.adapter.TypeDetailPokemonAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel


class TypeDetailPokemonsFragment : Fragment() {

    private val typeDetailViewModel: TypeDetailViewModel by activityViewModels()
    private lateinit var binding: TypeDetailPokemonsFragmentBinding
    private val itemWidthWithMargin = 117.0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        binding = TypeDetailPokemonsFragmentBinding.inflate(inflater, container, false)

        setupPokemonRecycler()

        return binding.root
    }

    private fun setupPokemonRecycler() {

        binding.recyclerPokemons.layoutManager = GridLayoutManager(
            requireContext(), Util.calculateNoOfColumns(requireContext(), itemWidthWithMargin)
        )

        var pokemonAdapter: TypeDetailPokemonAdapter

        typeDetailViewModel.typeDetail.observe(viewLifecycleOwner) {
            pokemonAdapter = TypeDetailPokemonAdapter(
                it.pokemon,
                requireContext(),
            )
            binding.recyclerPokemons.adapter = pokemonAdapter
        }

        val itemDecoration =
            ItemOffsetDecoration(requireContext(), R.dimen.type_detail_pokemon_recycler_offset);
        binding.recyclerPokemons.addItemDecoration(itemDecoration);

    }


    class ItemOffsetDecoration(private val mItemOffset: Int) : ItemDecoration() {
        constructor(
            context: Context,
            @DimenRes itemOffsetId: Int
        ) : this(context.resources.getDimensionPixelSize(itemOffsetId)) {
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
        }

    }


}