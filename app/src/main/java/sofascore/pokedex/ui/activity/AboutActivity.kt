package sofascore.pokedex.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sofascore.pokedex.R
import sofascore.pokedex.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { finish() }

    }
}