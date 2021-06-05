package sofascore.pokedex.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.TextWatcherAdapter
import sofascore.pokedex.R
import sofascore.pokedex.databinding.FragmentSearchBinding
import sofascore.pokedex.model.PokemonNamePhoto
import sofascore.pokedex.other.Util
import sofascore.pokedex.ui.activity.DetailPokemonActivity
import sofascore.pokedex.ui.adapter.PokemonAutoSuggestAdapter
import sofascore.pokedex.ui.adapter.PokemonPagedAdapter
import sofascore.pokedex.ui.viewmodel.FavoriteViewModel
import sofascore.pokedex.ui.viewmodel.RecentPokemonsViewModel
import sofascore.pokedex.ui.viewmodel.SearchResultsViewModel
import java.util.*

class SearchFragment : Fragment() {

    private val searchResultsViewModel: SearchResultsViewModel by viewModels()
    private val pagedPokemonsViewModel: RecentPokemonsViewModel by viewModels()
    private val favouritePokemonsViewModel: FavoriteViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding
    private lateinit var pagedAdapter: PokemonPagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupPokemonSearch()

        setupPagedPokemons()

        return binding.root
    }

    private fun setupPokemonSearch() {
        searchResultsViewModel.loadPokemons()

        binding.autoCompleteTextView.threshold = 0

        binding.autoCompleteTextView.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable) {
                filterResults(s.toString().lowercase(Locale.getDefault()))
            }
        })

        binding.autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                //because on click afterTextChanged is called and sets clicked item as text
                val item = parent!!.getItemAtPosition(0) as PokemonNamePhoto

                val intent =
                    Intent(this@SearchFragment.activity, DetailPokemonActivity()::class.java)

                intent.putExtra(DetailPokemonActivity.pokemonById, Util.getId(item.url))

                startActivity(intent)

                binding.autoCompleteTextView.clearListSelection()
                binding.autoCompleteTextView.text.clear();
            }
    }

    fun filterResults(search: String) {

        val customAdapter =
            PokemonAutoSuggestAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                searchResultsViewModel.allResults.value?.filter {
                    it.name.lowercase(Locale.getDefault())
                        .startsWith(search.lowercase(Locale.getDefault()))
                }
                    ?.toList() ?: mutableListOf()
            )

        binding.autoCompleteTextView.setAdapter(customAdapter)
        binding.autoCompleteTextView.refreshAutoCompleteResults()

    }

    private fun setupPagedPokemons() {
        pagedAdapter = PokemonPagedAdapter(requireContext(), favouritePokemonsViewModel)

        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavorite.adapter = pagedAdapter

        pagedPokemonsViewModel.pagingPokemonsList.observe(viewLifecycleOwner, {
            pagedAdapter.submitList(it)
        })
    }


    override fun onResume() {
        super.onResume()
        //TODO: fix
        pagedPokemonsViewModel.pagingPokemonsList.value?.dataSource?.invalidate()
    }

}