package sofascore.pokedex.model


import com.google.gson.annotations.SerializedName

data class AllPokemonsResponse(
    val count: Int, // 1118
    val next: String?, // https://pokeapi.co/api/v2/pokemon?offset=20&limit=20
    val previous: String?, // null
    @SerializedName("results")
    val results: List<PokemonNamePhoto>
)

data class PokemonNamePhoto(
    val name: String, // bulbasaur
    val url: String // https://pokeapi.co/api/v2/pokemon/1/
)