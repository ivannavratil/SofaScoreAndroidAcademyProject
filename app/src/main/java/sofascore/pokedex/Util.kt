package sofascore.pokedex

import android.content.Context
import android.content.res.Configuration
import java.util.*


object Util {


    @Suppress("DEPRECATION")
    fun changeLanguage(newLanguage: String, context: Context?) {
        val locale = Locale(newLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        config.locale = locale
        context?.resources?.updateConfiguration(config, null)

    }






}