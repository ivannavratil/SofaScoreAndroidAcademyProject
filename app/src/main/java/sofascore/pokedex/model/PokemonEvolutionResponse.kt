package sofascore.pokedex.model


import com.google.gson.annotations.SerializedName

data class PokemonEvolutionResponse(
    @SerializedName("baby_trigger_item")
    val babyTriggerItem: Any?, // null
    @SerializedName("chain")
    val chain: Chain,
    @SerializedName("id")
    val id: Int // 133
)

data class Chain(
    @SerializedName("evolution_details")
    val evolutionDetails: List<EvolutionDetail>,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>,
    @SerializedName("is_baby")
    val isBaby: Boolean, // false
    @SerializedName("species")
    val species: Species
)

data class EvolvesTo(
    @SerializedName("evolution_details")
    val evolutionDetails: List<EvolutionDetail>,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>,
    @SerializedName("is_baby")
    val isBaby: Boolean, // false
    @SerializedName("species")
    val species: Species
)


data class EvolutionDetail(
    @SerializedName("gender")
    val gender: Int?, // null
    @SerializedName("held_item")
    val heldItem: Any?, // null
    @SerializedName("item")
    val item: Any?, // null
    @SerializedName("known_move")
    val knownMove: Any?, // null
    @SerializedName("known_move_type")
    val knownMoveType: Any?, // null
    @SerializedName("location")
    val location: Any?, // null
    @SerializedName("min_affection")
    val minAffection: Int?, // null
    @SerializedName("min_beauty")
    val minBeauty: Int?, // null
    @SerializedName("min_happiness")
    val minHappiness: Int?, // null
    @SerializedName("min_level")
    val minLevel: Int?, // 18
    @SerializedName("needs_overworld_rain")
    val needsOverworldRain: Boolean, // false
    @SerializedName("party_species")
    val partySpecies: Any?, // null
    @SerializedName("party_type")
    val partyType: Any?, // null
    @SerializedName("relative_physical_stats")
    val relativePhysicalStats: Int?, // null
    @SerializedName("time_of_day")
    val timeOfDay: String,
    @SerializedName("trade_species")
    val tradeSpecies: Any?, // null
    @SerializedName("trigger")
    val trigger: Trigger,
    @SerializedName("turn_upside_down")
    val turnUpsideDown: Boolean // false
)

data class Species(
    @SerializedName("name")
    val name: String, // poochyena
    @SerializedName("url")
    val url: String // https://pokeapi.co/api/v2/pokemon-species/261/
)

data class Trigger(
    @SerializedName("name")
    val name: String, // level-up
    @SerializedName("url")
    val url: String // https://pokeapi.co/api/v2/evolution-trigger/1/
)
