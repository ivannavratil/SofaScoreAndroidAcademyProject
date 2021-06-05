package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import sofascore.pokedex.databinding.TypeDetailMovesFragmentBinding
import sofascore.pokedex.ui.adapter.TypeDetailMovesAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel


class TypeDetailMovesFragment : Fragment() {

    private val typeDetailViewModel: TypeDetailViewModel by activityViewModels()
    private lateinit var binding: TypeDetailMovesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        binding = TypeDetailMovesFragmentBinding.inflate(inflater, container, false)

        setupMovesRecycler()

        return binding.root
    }

    private fun setupMovesRecycler() {

        binding.movesRecycler.layoutManager = LinearLayoutManager(requireContext())

        var movesAdapter: TypeDetailMovesAdapter

        typeDetailViewModel.moveDetail.observe(viewLifecycleOwner) {
            movesAdapter = TypeDetailMovesAdapter(
                it,
                requireContext(),
            )
            binding.movesRecycler.adapter = movesAdapter
        }
    }


}