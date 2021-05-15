package sofascore.pokedex.model


import com.google.gson.annotations.SerializedName

data class AllPokemonsResponse(
    @SerializedName("count")
    val count: Int, // 1118
    @SerializedName("next")
    val next: String, // https://pokeapi.co/api/v2/pokemon?offset=20&limit=20
    @SerializedName("previous")
    val previous: Any?, // null
    @SerializedName("pokemonNameUrls")
    val pokemonNameUrls: List<PokemonNameUrl>
)

data class PokemonNameUrl(
    @SerializedName("name")
    val name: String, // bulbasaur
    @SerializedName("url")
    val url: String // https://pokeapi.co/api/v2/pokemon/1/
)