package sofascore.pokedex.model


import com.google.gson.annotations.SerializedName

data class TypeDetailResponse(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelations,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    @SerializedName("generation")
    val generation: Generation,
    @SerializedName("id")
    val id: Int, // 12
    @SerializedName("move_damage_class")
    val moveDamageClass: MoveDamageClass,
    @SerializedName("moves")
    val moves: List<Move>,
    @SerializedName("name")
    val name: String, // grass
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("pokemon")
    val pokemon: List<Pokemon>
) {
    data class DamageRelations(
        @SerializedName("double_damage_from")
        val doubleDamageFrom: List<DoubleDamageFrom>,
        @SerializedName("double_damage_to")
        val doubleDamageTo: List<DoubleDamageTo>,
        @SerializedName("half_damage_from")
        val halfDamageFrom: List<HalfDamageFrom>,
        @SerializedName("half_damage_to")
        val halfDamageTo: List<HalfDamageTo>,
        @SerializedName("no_damage_from")
        val noDamageFrom: List<Any>,
        @SerializedName("no_damage_to")
        val noDamageTo: List<Any>
    ) {
        data class DoubleDamageFrom(
            @SerializedName("name")
            val name: String, // flying
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/type/3/
        )

        data class DoubleDamageTo(
            @SerializedName("name")
            val name: String, // ground
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/type/5/
        )

        data class HalfDamageFrom(
            @SerializedName("name")
            val name: String, // ground
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/type/5/
        )

        data class HalfDamageTo(
            @SerializedName("name")
            val name: String, // flying
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/type/3/
        )
    }

    data class GameIndice(
        @SerializedName("game_index")
        val gameIndex: Int, // 22
        @SerializedName("generation")
        val generation: Generation
    ) {
        data class Generation(
            @SerializedName("name")
            val name: String, // generation-i
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/generation/1/
        )
    }

    data class Generation(
        @SerializedName("name")
        val name: String, // generation-i
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/generation/1/
    )

    data class MoveDamageClass(
        @SerializedName("name")
        val name: String, // special
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/move-damage-class/3/
    )

    data class Move(
        @SerializedName("name")
        val name: String, // vine-whip
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/move/22/
    )

    data class Name(
        @SerializedName("language")
        val language: Language,
        @SerializedName("name")
        val name: String // くさ
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // ja-Hrkt
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/1/
        )
    }

    data class Pokemon(
        @SerializedName("pokemon")
        val pokemon: Pokemon,
        @SerializedName("slot")
        val slot: Int // 1
    ) {
        data class Pokemon(
            @SerializedName("name")
            val name: String, // bulbasaur
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/pokemon/1/
        )
    }
}