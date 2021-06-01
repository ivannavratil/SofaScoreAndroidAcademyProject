package sofascore.pokedex.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import sofascore.pokedex.databinding.TypeDetailPokemonsFragmentBinding
import sofascore.pokedex.ui.adapter.TypeDetailPokemonAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel


class TypeDetailPokemonsFragment : Fragment() {

    private val typeDetailViewModel: TypeDetailViewModel by viewModels()
    private lateinit var binding: TypeDetailPokemonsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        binding = TypeDetailPokemonsFragmentBinding.inflate(inflater, container, false)

        binding.recyclerPokemons.layoutManager = GridLayoutManager(
            requireContext(), calculateNoOfColumns(requireContext(), 117.0)
        )

        var pokemonAdapter: TypeDetailPokemonAdapter

        if (typeDetailViewModel.typeDetail.value != null) {
            pokemonAdapter = TypeDetailPokemonAdapter(
                typeDetailViewModel.typeDetail.value!!.pokemon,
                requireContext(),
            )
            binding.recyclerPokemons.adapter = pokemonAdapter
        }

        typeDetailViewModel.typeDetail.observe(viewLifecycleOwner) {
            pokemonAdapter = TypeDetailPokemonAdapter(
                it.pokemon,
                requireContext(),
            )
            binding.recyclerPokemons.adapter = pokemonAdapter
        }

        //TODO: fix
        typeDetailViewModel.getDetailTypeAndMove(1, requireContext())


        return binding.root
    }

    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Double
    ): Int {
        val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt() // +0.5 for correct rounding to int.
    }


}