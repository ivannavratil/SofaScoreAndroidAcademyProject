package sofascore.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sofascore.pokedex.databinding.FragmentFavoritesBinding
import sofascore.pokedex.model.Pokemon
import sofascore.pokedex.ui.adapter.FavoritePokemonsAdapter
import sofascore.pokedex.ui.viewmodel.FavoriteViewModel
import java.util.*


class FavoritesFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(requireContext())

        var adapter: FavoritePokemonsAdapter? = null

        favoriteViewModel.favoritePokemons.observe(viewLifecycleOwner) {
            adapter = FavoritePokemonsAdapter(
                it as ArrayList<Pokemon>,
                requireContext(),
                favoriteViewModel
            )
            binding.recyclerViewFavorite.adapter = adapter
        }


        val itemTouchHelper = ItemTouchHelper(simpleCallback)

        binding.editPen.setOnClickListener {
            adapter?.changeHandlerVisibility()
            binding.editPen.visibility = View.GONE
            binding.done.visibility = View.VISIBLE
            itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavorite)
        }

        binding.done.setOnClickListener {
            adapter?.changeHandlerVisibility()
            binding.editPen.visibility = View.VISIBLE
            binding.done.visibility = View.GONE
            itemTouchHelper.attachToRecyclerView(null)
        }

        return binding.root
    }


    private var simpleCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                val adapter = (recyclerView.adapter as FavoritePokemonsAdapter)

                favoriteViewModel.swapFavoritePokemonsOrder(
                    adapter.getItem(fromPosition),
                    adapter.getItem(toPosition),
                    requireContext()
                )

                Collections.swap(
                    favoriteViewModel.favoritePokemons.value!!,
                    fromPosition,
                    toPosition
                )

                recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.loadFavoritePokemons(requireContext())
    }
}