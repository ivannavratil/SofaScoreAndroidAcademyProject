package sofascore.pokedex

import android.content.Context
import android.content.res.Configuration
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


object Util {


    private val pattern: Pattern = Pattern.compile("/\\d+/")


    fun getId(url: String): Int {
        val matcher: Matcher = pattern.matcher(url)
        matcher.find()
        val idWithSlashes = matcher.group(0)!!
        return Integer.parseInt(idWithSlashes.substring(1, idWithSlashes.length - 1));
    }


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