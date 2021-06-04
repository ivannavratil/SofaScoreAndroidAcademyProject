package sofascore.pokedex.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import sofascore.pokedex.Util.capitalize
import sofascore.pokedex.databinding.ActivityTypeDetailBinding
import sofascore.pokedex.ui.adapter.SectionsPagerAdapter
import sofascore.pokedex.ui.viewmodel.TypeDetailViewModel
import java.util.*


class TypeDetailActivity : AppCompatActivity() {

    private val typeDetailViewModel: TypeDetailViewModel by viewModels()
    private lateinit var binding: ActivityTypeDetailBinding

    companion object {
        const val type = "TYPE_BY_ID";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTypeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)


        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.toolbar.navigationIcon!!.setTint(Color.BLACK)


        val type = intent.getIntExtra(type, -1);

        typeDetailViewModel.getDetailTypeAndMove(
            type,
            applicationContext
        )


        typeDetailViewModel.typeDetail.observe(this) {

            val name = typeDetailViewModel.typeDetail.value!!.name;

            val identifier = resources.getIdentifier(
                "flat_pokemon_type_" + name
                    .lowercase(Locale.getDefault()),
                "color",
                this.packageName
            )

            val color = resources.getColor(identifier, null)
            binding.toolbar.setBackgroundColor(color)
            binding.tabs.setBackgroundColor(color)

            helper(binding.tabs, identifier)

            binding.realTitle.text =
                typeDetailViewModel.typeDetail.value!!.name.capitalize() + " Type"

        }


    }


    fun helper(tabLayout: TabLayout, identifier: Int) {
        val tabStrip = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabStrip.childCount) {
            val tabView: View? = tabStrip.getChildAt(i)
            if (tabView != null) {
                val paddingStart: Int = tabView.paddingStart
                val paddingTop: Int = tabView.paddingTop
                val paddingEnd: Int = tabView.paddingEnd
                val paddingBottom: Int = tabView.paddingBottom
                ViewCompat.setBackground(
                    tabView,
                    AppCompatResources.getDrawable(tabView.getContext(), identifier)
                )
                ViewCompat.setPaddingRelative(
                    tabView,
                    paddingStart,
                    paddingTop,
                    paddingEnd,
                    paddingBottom
                )
            }
        }
    }

}