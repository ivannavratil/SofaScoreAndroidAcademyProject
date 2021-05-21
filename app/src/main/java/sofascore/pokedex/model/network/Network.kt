package sofascore.pokedex.model.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime


class Network {

    val service: PokedexService
    private val baseUrl = "https://pokeapi.co/api/v2/"


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonDeserializer { json, _, _ -> deserializeLDT(json) }
            )
            .registerTypeAdapter(
                LocalDate::class.java,
                JsonDeserializer { json, _, _ -> deserializeLD(json) }
            )
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        service = retrofit.create(PokedexService::class.java)


    }

    private fun deserializeLD(json: JsonElement): LocalDate {
        return LocalDate.parse(json.asJsonPrimitive.asString)
    }

    private fun deserializeLDT(json: JsonElement): LocalDateTime {
        return ZonedDateTime.parse(json.asJsonPrimitive.asString).toLocalDateTime()
    }

}