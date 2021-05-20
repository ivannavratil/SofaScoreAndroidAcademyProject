package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import sofascore.pokedex.databinding.FragmentSearchBinding
import sofascore.pokedex.ui.adapter.PagedPokemonNamePhotoAdapter
import sofascore.pokedex.ui.viewmodel.RecentPokemonsViewModel

class SearchFragment : Fragment() {

    private val pagedPokemonsViewModel: RecentPokemonsViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val adapter = PagedPokemonNamePhotoAdapter()

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewRecent.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRecent.adapter = adapter

        pagedPokemonsViewModel.pagingPokemonsList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}