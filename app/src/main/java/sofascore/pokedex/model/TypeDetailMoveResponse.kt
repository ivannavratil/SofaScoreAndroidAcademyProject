package sofascore.pokedex.model


import com.google.gson.annotations.SerializedName

data class TypeDetailMoveResponse(
    @SerializedName("accuracy")
    val accuracy: Int, // 100
    @SerializedName("contest_combos")
    val contestCombos: ContestCombos,
    @SerializedName("contest_effect")
    val contestEffect: ContestEffect,
    @SerializedName("contest_type")
    val contestType: ContestType,
    @SerializedName("damage_class")
    val damageClass: DamageClass?,
    @SerializedName("effect_chance")
    val effectChance: Any?, // null
    @SerializedName("effect_changes")
    val effectChanges: List<Any>,
    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("generation")
    val generation: Generation,
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("learned_by_pokemon")
    val learnedByPokemon: List<LearnedByPokemon>,
    @SerializedName("machines")
    val machines: List<Any>,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("name")
    val name: String, // pound
    @SerializedName("names")
    val names: List<Name>,
    @SerializedName("past_values")
    val pastValues: List<Any>,
    @SerializedName("power")
    val power: Int, // 40
    @SerializedName("pp")
    val pp: Int, // 35
    @SerializedName("priority")
    val priority: Int, // 0
    @SerializedName("stat_changes")
    val statChanges: List<Any>,
    @SerializedName("super_contest_effect")
    val superContestEffect: SuperContestEffect,
    @SerializedName("target")
    val target: Target,
    @SerializedName("type")
    val type: Type
) {
    data class ContestCombos(
        @SerializedName("normal")
        val normal: Normal,
        @SerializedName("super")
        val superX: Super
    ) {
        data class Normal(
            @SerializedName("use_after")
            val useAfter: Any?, // null
            @SerializedName("use_before")
            val useBefore: List<UseBefore>
        ) {
            data class UseBefore(
                @SerializedName("name")
                val name: String, // double-slap
                @SerializedName("url")
                val url: String // https://pokeapi.co/api/v2/move/3/
            )
        }

        data class Super(
            @SerializedName("use_after")
            val useAfter: Any?, // null
            @SerializedName("use_before")
            val useBefore: Any? // null
        )
    }

    data class ContestEffect(
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/contest-effect/1/
    )

    data class ContestType(
        @SerializedName("name")
        val name: String, // tough
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/contest-type/5/
    )

    data class DamageClass(
        @SerializedName("name")
        val name: String, // physical
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/move-damage-class/2/
    )

    data class EffectEntry(
        @SerializedName("effect")
        val effect: String, // Inflicts regular damage.
        @SerializedName("language")
        val language: Language,
        @SerializedName("short_effect")
        val shortEffect: String // Inflicts regular damage with no additional effect.
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // en
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/9/
        )
    }

    data class FlavorTextEntry(
        @SerializedName("flavor_text")
        val flavorText: String, // Pounds with fore­legs or tail.
        @SerializedName("language")
        val language: Language,
        @SerializedName("version_group")
        val versionGroup: VersionGroup
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // en
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/9/
        )

        data class VersionGroup(
            @SerializedName("name")
            val name: String, // gold-silver
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/version-group/3/
        )
    }

    data class Generation(
        @SerializedName("name")
        val name: String, // generation-i
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/generation/1/
    )

    data class LearnedByPokemon(
        @SerializedName("name")
        val name: String, // clefairy
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon/35/
    )

    data class Meta(
        @SerializedName("ailment")
        val ailment: Ailment,
        @SerializedName("ailment_chance")
        val ailmentChance: Int, // 0
        @SerializedName("category")
        val category: Category?,
        @SerializedName("crit_rate")
        val critRate: Int, // 0
        @SerializedName("drain")
        val drain: Int, // 0
        @SerializedName("flinch_chance")
        val flinchChance: Int, // 0
        @SerializedName("healing")
        val healing: Int, // 0
        @SerializedName("max_hits")
        val maxHits: Int?, // null
        @SerializedName("max_turns")
        val maxTurns: Int?, // null
        @SerializedName("min_hits")
        val minHits: Int?, // null
        @SerializedName("min_turns")
        val minTurns: Int?, // null
        @SerializedName("stat_chance")
        val statChance: Int // 0
    ) {
        data class Ailment(
            @SerializedName("name")
            val name: String, // none
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/move-ailment/0/
        )

        data class Category(
            @SerializedName("name")
            val name: String, // damage
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/move-category/0/
        )
    }

    data class Name(
        @SerializedName("language")
        val language: Language,
        @SerializedName("name")
        val name: String // はたく
    ) {
        data class Language(
            @SerializedName("name")
            val name: String, // ja-Hrkt
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/language/1/
        )
    }

    data class SuperContestEffect(
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/super-contest-effect/5/
    )

    data class Target(
        @SerializedName("name")
        val name: String, // selected-pokemon
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/move-target/10/
    )

    data class Type(
        @SerializedName("name")
        val name: String, // normal
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/type/1/
    )
}