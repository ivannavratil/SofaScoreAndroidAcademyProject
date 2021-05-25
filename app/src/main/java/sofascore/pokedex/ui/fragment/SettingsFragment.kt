package sofascore.pokedex.ui.fragment

import android.content.Context
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
import androidx.lifecycle.ViewModelProvider
import sofascore.pokedex.R
import sofascore.pokedex.Util
import sofascore.pokedex.databinding.FragmentSettingsBinding
import sofascore.pokedex.ui.activity.AboutActivity
import sofascore.pokedex.ui.activity.MainActivity
import sofascore.pokedex.ui.viewmodel.NotificationsViewModel


class SettingsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.moreInfo.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity()::class.java)
            startActivity(intent)
        }

        val spinner: Spinner = binding.language

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.available_languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

            if (sharedPref!!.getInt("LANGUAGE", -1) == -1) {
                with(sharedPref.edit()) {
                    putInt("LANGUAGE", 0)
                    apply()
                }

            }

            spinner.setSelection(sharedPref.getInt("LANGUAGE", -1))
            println("stvaranje fragment, selected item =>" + spinner.selectedItemId)
        }



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                println("position => $position")

                if (activity?.getPreferences(Context.MODE_PRIVATE)!!
                        .getInt("LANGUAGE", -1) == spinner.selectedItemPosition
                )
                    return


                val languageShort =
                    (resources.getStringArray(R.array.available_languages_short)[spinner.selectedItemPosition]) // 12, 16, 20


                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
                with(sharedPref.edit()) {
                    putInt("LANGUAGE", position)
                    apply()
                }

                Util.changeLanguage(languageShort, requireContext())
                //TODO: fix
                //restartApp()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

        }


        return root
    }

    private fun restartApp() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, MainActivity::class.java)
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