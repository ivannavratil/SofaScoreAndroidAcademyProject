package sofascore.pokedex.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.Util
import sofascore.pokedex.Util.capitalize
import sofascore.pokedex.databinding.ActivityDetailPokemonBinding
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.ui.adapter.StatsAdapter
import sofascore.pokedex.ui.adapter.TypeAdapter
import sofascore.pokedex.ui.viewmodel.DetailPokemonViewModel


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


        val typeRecycler = binding.pokemonDetails.pokemonMain.typeRecyler
        typeRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val statsRecycler = binding.pokemonDetails.pokemonStats.statsRecycler
        statsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        detailPokemonViewModel.detailPokemon.observe(this, {
            setupUI(it)

            val typeAdapter = TypeAdapter(it.types, this)
            typeRecycler.adapter = typeAdapter

            val statsAdapter = StatsAdapter(it.stats, this)
            binding.pokemonDetails.pokemonStats.totalValue.text = it.stats.map { a -> a.baseStat }.sum().toString()

            statsRecycler.adapter = statsAdapter

        })


    }

    private fun setupUI(detailPokemon: DetailPokemonResponse) {

        binding.toolbar.setNavigationOnClickListener { finish() }

        setupMainInfo(detailPokemon)
        setupWeightAndHeight(detailPokemon)

        setupAbilities(detailPokemon)


    }

    private fun setupAbilities(detailPokemon: DetailPokemonResponse) {
        val abilities = binding.pokemonDetails.pokemonAbilities;
        val abilities1 = detailPokemon.abilities;

        abilities.firstAbility.text = abilities1[0].ability.name.capitalize()
        abilities.secondAbilityHidden.visibility =
            if (abilities1[0].isHidden) View.GONE else View.VISIBLE

        abilities.secondAbility.text = abilities1[1].ability.name.capitalize()
        abilities.secondAbilityHidden.visibility =
            if (abilities1[1].isHidden) View.GONE else View.VISIBLE
    }

    private fun setupMainInfo(detailPokemon: DetailPokemonResponse) {
        binding.realTitle.text = detailPokemon.name.capitalize()
        binding.pokemonDetails.pokemonMain.pokemonName.text = detailPokemon.name.capitalize()
        binding.pokemonDetails.pokemonMain.pokedexVal.text = detailPokemon.getFormattedId()
        binding.pokemonDetails.pokemonMain.pokemonPhoto.load(detailPokemon.getAvatarUrl())
    }

    private fun setupWeightAndHeight(detailPokemon: DetailPokemonResponse) {
        val pokemonHeightWeight = binding.pokemonDetails.pokemonHeightWeight;
        pokemonHeightWeight.includedHeight.image.load(R.drawable.ic_height)
        pokemonHeightWeight.includedHeight.attKey.text = resources.getString(R.string.height)
        pokemonHeightWeight.includedHeight.attVal.text =
            Util.heightToFormattedHeight(detailPokemon.height)
        pokemonHeightWeight.includedWeight.image.load(R.drawable.ic_weight)
        pokemonHeightWeight.includedWeight.attKey.text = resources.getString(R.string.weight)
        pokemonHeightWeight.includedWeight.attVal.text =
            Util.weightToFormattedWeight(detailPokemon.weight)
    }
}