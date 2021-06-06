package sofascore.pokedex.model


import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("base_happiness")
    val baseHappiness: Int, // 70
    @SerializedName("capture_rate")
    val captureRate: Int, // 45
    @SerializedName("color")
    val color: Color,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroup>,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: Any?, // null
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("form_descriptions")
    val formDescriptions: List<Any>,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean, // false
    @SerializedName("gender_rate")
    val genderRate: Int, // 1
    @SerializedName("genera")
    val genera: List<Genera>,
    @SerializedName("generation")
    val generation: Generation,
    @SerializedName("growth_rate")
    val growthRate: GrowthRate,
    @SerializedName("habitat")
    val habitat: Habitat,
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean, // false
    @SerializedName("hatch_counter")
    val hatchCounter: Int, // 35
    @SerializedName("id")
    val id: Int, // 133
    @SerializedName("is_baby")
    val isBaby: Boolean, // false
    @SerializedName("is_legendary")
    val isLegendary: Boolean, // false
    @SerializedName("is_mythical")
    val isMythical: Boolean, // false
    @SerializedName("name")
    val name: String, // eevee
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("order")
    val order: Int, // 157
    @SerializedName("pal_park_encounters")
    val palParkEncounters: List<PalParkEncounter>,
    @SerializedName("pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber>,
    @SerializedName("shape")
    val shape: Shape,
    @SerializedName("varieties")
    val varieties: List<Variety>
) {
    data class Color(
        @SerializedName("name")
        val name: String, // brown
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon-color/3/
    )

    data class EggGroup(
        @SerializedName("name")
        val name: String, // ground
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/egg-group/5/
    )

    data class EvolutionChain(
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/evolution-chain/67/
    )

    data class FlavorTextEntry(
        @SerializedName("flavor_text")
        val flavorText: String, // Its genetic codeis irregular.It may mutate ifit is exposed toradiation fromelement STONEs.
        @SerializedName("language")
        val language: Language,
        @SerializedName("version")
        val version: Version
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // en
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/9/
        )

        data class Version(
            @SerializedName("name")
            val name: String, // red
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/version/1/
        )
    }

    data class Genera(
        @SerializedName("genus")
        val genus: String, // しんかポケモン
        @SerializedName("language")
        val language: Language
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // ja-Hrkt
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/1/
        )
    }

    data class Generation(
        @SerializedName("name")
        val name: String, // generation-i
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/generation/1/
    )

    data class GrowthRate(
        @SerializedName("name")
        val name: String, // medium
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/growth-rate/2/
    )

    data class Habitat(
        @SerializedName("name")
        val name: String, // urban
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon-habitat/8/
    )

    data class Name(
        @SerializedName("language")
        val language: Language,
        @SerializedName("name")
        val name: String // イーブイ
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // ja-Hrkt
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/1/
        )
    }

    data class PalParkEncounter(
        @SerializedName("area")
        val area: Area,
        @SerializedName("base_score")
        val baseScore: Int, // 90
        @SerializedName("rate")
        val rate: Int // 3
    ) {
        data class Area(
            @SerializedName("name")
            val name: String, // field
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/pal-park-area/2/
        )
    }

    data class PokedexNumber(
        @SerializedName("entry_number")
        val entryNumber: Int, // 133
        @SerializedName("pokedex")
        val pokedex: Pokedex
    ) {
        data class Pokedex(
            @SerializedName("name")
            val name: String, // national
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/pokedex/1/
        )
    }

    data class Shape(
        @SerializedName("name")
        val name: String, // quadruped
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon-shape/8/
    )

    data class Variety(
        @SerializedName("is_default")
        val isDefault: Boolean, // true
        @SerializedName("pokemon")
        val pokemon: Pokemon
    ) {
        data class Pokemon(
            @SerializedName("name")
            val name: String, // eevee
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/pokemon/133/
        )
    }
}