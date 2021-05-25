package sofascore.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.Util.capitalize
import sofascore.pokedex.databinding.ActivityDetailPokemonBinding
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.ui.viewmodel.DetailPokemonViewModel
import java.util.*


class DetailPokemonActivity : AppCompatActivity() {

    private val detailPokemonViewModel: DetailPokemonViewModel by viewModels()
    private lateinit var binding: ActivityDetailPokemonBinding

    companion object {
        const val pokemonById = "BY_ID";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        detailPokemonViewModel.getDetailedPokemonInfo(
            intent.getIntExtra(pokemonById, -1),
            applicationContext
        )

        detailPokemonViewModel.detailPokemon.observe(this, {
            setupUI(it)
        })


    }

    private fun setupUI(detailPokemon: DetailPokemonResponse) {

        binding.toolbar.setNavigationOnClickListener { finish() }


        binding.realTitle.text = detailPokemon.name.capitalize()

        binding.pokemonDetails.pokemonMain.pokemonName.text = detailPokemon.name.capitalize()

        binding.pokemonDetails.pokemonMain.pokedexVal.text = detailPokemon.getFormattedId()

        binding.pokemonDetails.pokemonMain.pokemonPhoto.load(detailPokemon.getAvatarUrl())

        val pokemonHeightWeight = binding.pokemonDetails.pokemonHeightWeight;

        pokemonHeightWeight.includedHeight.image.load(R.drawable.ic_height)
        pokemonHeightWeight.includedHeight.attKey.text = resources.getString(R.string.height)
        pokemonHeightWeight.includedHeight.attVal.text = detailPokemon.height.toString()

        pokemonHeightWeight.includedWeight.image.load(R.drawable.ic_weight)
        pokemonHeightWeight.includedWeight.attKey.text = resources.getString(R.string.weight)
        pokemonHeightWeight.includedWeight.attVal.text = detailPokemon.weight.toString()


    }
}