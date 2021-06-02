package sofascore.pokedex.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import sofascore.pokedex.LanguageHelper
import sofascore.pokedex.PopUpClass
import sofascore.pokedex.R
import sofascore.pokedex.databinding.FragmentSettingsBinding
import sofascore.pokedex.ui.activity.AboutActivity
import sofascore.pokedex.ui.activity.MainActivity
import sofascore.pokedex.ui.viewmodel.FavoriteViewModel

const val LANGUAGE_EN = "en"
const val LANGUAGE_HR = "hr"

class SettingsFragment : Fragment() {


    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var firstSelection = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.moreInfo.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity()::class.java)
            startActivity(intent)
        }

        binding.clearFavourites.setOnClickListener {
            val popup = PopUpClass()
            popup.showPopupWindow(it, favoriteViewModel)
        }

        val spinner: Spinner = binding.language

        val currentLanguageCode = LanguageHelper.getPreferredLanguage(requireContext())

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.available_languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        if (currentLanguageCode == LANGUAGE_HR) {
            binding.language.setSelection(1)
        }


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (firstSelection) {
                    firstSelection = false
                } else {
                    val item = parent.getItemAtPosition(position) as String
                    val tempLanguageCode = languageStringToCode(item)
                    if (tempLanguageCode != currentLanguageCode) {
                        LanguageHelper.setPreferredLanguage(requireContext(), tempLanguageCode)
                        restartApp()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }
        return binding.root
    }

    private fun languageStringToCode(languageString: String): String {
        return when (languageString) {
            requireContext().getString(R.string.language_en) -> LANGUAGE_EN
            requireContext().getString(R.string.language_hr) -> LANGUAGE_HR
            else -> ""
        }
    }

    private fun restartApp() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(activity, MainActivity::class.java)
            val cn = intent.component
            val mainIntent = Intent.makeRestartActivityTask(cn)
            startActivity(mainIntent)
            Process.killProcess(Process.myPid())
        }, 300)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}