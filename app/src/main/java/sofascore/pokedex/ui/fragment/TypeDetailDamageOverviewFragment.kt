package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sofascore.pokedex.R

class TypeDetailDamageOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = TypeDetailDamageOverviewFragment()
    }

    private lateinit var viewModel: TypeDetailDamageOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.type_detail_damage_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TypeDetailDamageOverviewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}