package sofascore.pokedex.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import sofascore.pokedex.databinding.ActivityTypeDetailBinding
import sofascore.pokedex.ui.adapter.SectionsPagerAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel


class TypeDetailActivity : AppCompatActivity() {

    private val typeDetailViewModel: TypeDetailViewModel by viewModels()
    private lateinit var binding: ActivityTypeDetailBinding

    companion object {
        const val type = "TYPE_BY_ID";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        typeDetailViewModel.getDetailedPokemonInfo(
            intent.getIntExtra(DetailPokemonActivity.pokemonById, -1),
            applicationContext
        )

        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        //TODO: doesn't work
        binding.toolbar.setNavigationOnClickListener { finish() }

    }
}