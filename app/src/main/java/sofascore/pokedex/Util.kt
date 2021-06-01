package sofascore.pokedex

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.roundToInt


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

    fun weightToFormattedWeight(weight: Int): String {
        val weight2 = weight / 10.0;
        val weightInLbs = 2.20462262185 * weight2
        return String.format("%.1f", weightInLbs) + "lbs." + " (${weight2}kg)"
    }

    fun heightToFormattedHeight(height: Int): String {

        val height2 = height / 10.0
        val feet = height2 / 0.3048;

        val numberD: String = feet.toString()

        val afterDecimal = numberD.substring(numberD.indexOf(".")).toDouble() / 0.08333

        return """${String.format("%.0f", feet)}'${
            String.format(
                "%02d",
                afterDecimal.roundToInt()
            )
        }" (${height2}m)"""
    }

    fun String.capitalize(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() };
    }

}