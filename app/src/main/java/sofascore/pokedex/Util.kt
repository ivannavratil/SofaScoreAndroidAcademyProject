package sofascore.pokedex

import android.content.Context
import android.content.res.Configuration
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


object Util {


    private val idPattern: Pattern = Pattern.compile("/\\d+/")
    private val offsetPattern: Pattern = Pattern.compile("offset=\\d+")


    fun getId(url: String): Int {
        val matcher: Matcher = idPattern.matcher(url)
        matcher.find()
        val idWithSlashes = matcher.group(0)!!
        return Integer.parseInt(idWithSlashes.substring(1, idWithSlashes.length - 1));
    }

    fun getOffset(string: String): Int {
        val matcher: Matcher = offsetPattern.matcher(string)
        matcher.find()
        val offsetWithNumber = matcher.group(0)!!
        return Integer.parseInt(offsetWithNumber.substring(offsetWithNumber.indexOf("=") + 1))

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