package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sofascore.pokedex.R

class TypeDetailMovesFragment : Fragment() {

    companion object {
        fun newInstance() = TypeDetailMovesFragment()
    }

    private lateinit var viewModel: TypeDetailMovesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.type_detail_moves_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TypeDetailMovesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}