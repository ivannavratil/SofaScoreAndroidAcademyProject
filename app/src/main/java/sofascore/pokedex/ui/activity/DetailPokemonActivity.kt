package sofascore.pokedex.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sofascore.pokedex.R
import sofascore.pokedex.databinding.ActivityDetailPokemonBinding
import sofascore.pokedex.model.db.DetailPokemonResponse
import sofascore.pokedex.other.Util
import sofascore.pokedex.other.Util.capitalize
import sofascore.pokedex.ui.adapter.PokemonDetailAbilitiesAdapter
import sofascore.pokedex.ui.adapter.PokemonDetailStatsAdapter
import sofascore.pokedex.ui.adapter.PokemonDetailTypeAdapter
import sofascore.pokedex.ui.adapter.PokemonDetailVerticalEvolutionAdapter
import sofascore.pokedex.ui.viewmodel.DetailPokemonViewModel


class DetailPokemonActivity : AppCompatActivity() {

    private val detailPokemonViewModel: DetailPokemonViewModel by viewModels()
    private lateinit var binding: ActivityDetailPokemonBinding
    private val typeItemWidth = 85.0
    private val nonRecyclerWidth = 16 + 144 + 16


    companion object {
        const val pokemonById = "BY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getIntExtra(pokemonById, -1)

        detailPokemonViewModel.getInfo(
            id,
            applicationContext
        )

        val (typeRecycler, statsRecycler, abilitiesRecycler, evolutionRecycler) = setupRecyclerViews()


        detailPokemonViewModel.detailPokemon.observe(this, {
            setupUI(it)

            val typeAdapter = PokemonDetailTypeAdapter(it.types, this)
            typeRecycler.adapter = typeAdapter

            val statsAdapter = PokemonDetailStatsAdapter(it.stats, this)
            binding.pokemonDetails.pokemonStats.totalValue.text =
                it.stats.map { a -> a.baseStat }.sum().toString()
            statsRecycler.adapter = statsAdapter

            val abilitiesAdapter = PokemonDetailAbilitiesAdapter(it.abilities, this)
            abilitiesRecycler.adapter = abilitiesAdapter
        })

        detailPokemonViewModel.evaluation.observe(this, {
            val evolutionAdapter = PokemonDetailVerticalEvolutionAdapter(it, this)
            evolutionRecycler.adapter = evolutionAdapter

        })

        detailPokemonViewModel.favourite.observe(this, {
            binding.pokemonFavorite.setImageResource(if (detailPokemonViewModel.favourite.value == true) R.drawable.ic_star_1 else R.drawable.ic_star_0)
        })


        binding.pokemonFavorite.setOnClickListener {
            detailPokemonViewModel.flipFavourite(
                applicationContext
            )
        }


    }

    private fun setupRecyclerViews(): List<RecyclerView> {
        val typeRecycler = binding.pokemonDetails.pokemonMain.typeRecycler
        typeRecycler.layoutManager = GridLayoutManager(
            this,
            Util.calculateNoOfColumns(this, typeItemWidth, nonRecyclerWidth)
        )

        val statsRecycler = binding.pokemonDetails.pokemonStats.statsRecycler
        statsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val abilitiesRecycler = binding.pokemonDetails.abilitiesRecycler
        abilitiesRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val evolutionRecycler = binding.pokemonDetails.includedRecycler.verticalRecycler
        evolutionRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        return listOf(typeRecycler, statsRecycler, abilitiesRecycler, evolutionRecycler)
    }

    private fun setupUI(detailPokemon: DetailPokemonResponse) {

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.toolbar.navigationIcon!!.setTint(Color.BLACK)

        setupMainInfo(detailPokemon)
        setupWeightAndHeight(detailPokemon)


    }

    private fun setupMainInfo(detailPokemon: DetailPokemonResponse) {
        binding.realTitle.text = detailPokemon.name.capitalize()
        binding.pokemonDetails.pokemonMain.pokemonName.text = detailPokemon.name.capitalize()
        binding.pokemonDetails.pokemonMain.pokedexVal.text = detailPokemon.getFormattedId()
        binding.pokemonDetails.pokemonMain.pokemonPhoto.load(detailPokemon.getAvatarUrl())
    }

    private fun setupWeightAndHeight(detailPokemon: DetailPokemonResponse) {
        val pokemonHeightWeight = binding.pokemonDetails.pokemonHeightWeight
        pokemonHeightWeight.includedHeight.image.load(R.drawable.ic_height)
        pokemonHeightWeight.includedHeight.attKey.text = resources.getString(R.string.height)
        pokemonHeightWeight.includedHeight.attVal.text =
            Util.heightToFormattedHeight(detailPokemon.height)
        pokemonHeightWeight.includedWeight.image.load(R.drawable.ic_weight)
        pokemonHeightWeight.includedWeight.attKey.text = resources.getString(R.string.weight)
        pokemonHeightWeight.includedWeight.attVal.text =
            Util.weightToFormattedWeight(detailPokemon.weight)
    }

    override fun onRestart() {
        super.onRestart()

        detailPokemonViewModel.refreshFavoriteStatus(
            detailPokemonViewModel.detailPokemon.value!!.id,
            this
        )
    }
}