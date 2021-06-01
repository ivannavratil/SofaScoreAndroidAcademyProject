package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sofascore.pokedex.R
import sofascore.pokedex.ui.viewmodel.TypeDetailPokemonsViewModel

class TypeDetailPokemonsFragment : Fragment() {

    companion object {
        fun newInstance() = TypeDetailPokemonsFragment()
    }

    private lateinit var viewModel: TypeDetailPokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.type_detail_pokemons_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TypeDetailPokemonsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}