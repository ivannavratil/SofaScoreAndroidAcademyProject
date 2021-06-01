package sofascore.pokedex.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import sofascore.pokedex.databinding.ActivityTypeDetailBinding
import sofascore.pokedex.ui.adapter.SectionsPagerAdapter


class TypeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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