package sofascore.pokedex.model.db


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DetailPokemonResponse(
    @SerializedName("abilities")
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int, // 64
    @SerializedName("forms")
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<Any>,
    @SerializedName("height")
    val height: Int, // 7 !!!!!
    @SerializedName("held_items")
    val heldItems: List<Any>,
    @SerializedName("id")
    val id: Int, // 1 !!!!!
    @SerializedName("is_default")
    val isDefault: Boolean, // true
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String, // https://pokeapi.co/api/v2/pokemon/1/encounters
    @SerializedName("moves")
    val moves: List<Move>,
    @SerializedName("name")
    val name: String, // bulbasaur !!!!!
    @SerializedName("order")
    val order: Int, // 1
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    @SerializedName("species")
    val species: Species,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("stats")
    val stats: List<Stat>, // !!!!!
    @SerializedName("types")
    val types: List<Type>, // !!!!!
    @SerializedName("weight")
    val weight: Int, // 69 !!!!!

) {
    data class Ability( ///!!!!!
        @SerializedName("ability")
        val ability: Ability,
        @SerializedName("is_hidden")
        val isHidden: Boolean, // false
        @SerializedName("slot")
        val slot: Int // 1
    ) {
        data class Ability(
            @SerializedName("name")
            val name: String, // overgrow
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/ability/65/
        )
    }

    data class Form(
        @SerializedName("name")
        val name: String, // bulbasaur
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon-form/1/
    )

    data class Move(
        @SerializedName("move")
        val move: Move,
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail>
    ) {
        data class Move(
            @SerializedName("name")
            val name: String, // razor-wind
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/move/13/
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int, // 0
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod,
            @SerializedName("version_group")
            val versionGroup: VersionGroup
        ) {
            data class MoveLearnMethod(
                @SerializedName("name")
                val name: String, // egg
                @SerializedName("url")
                val url: String // https://pokeapi.co/api/v2/move-learn-method/2/
            )

            data class VersionGroup(
                @SerializedName("name")
                val name: String, // gold-silver
                @SerializedName("url")
                val url: String // https://pokeapi.co/api/v2/version-group/3/
            )
        }
    }

    data class Species(
        @SerializedName("name")
        val name: String, // bulbasaur
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon-species/1/
    )

    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png
        @SerializedName("back_female")
        val backFemale: Any?, // null
        @SerializedName("back_shiny")
        val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png
        @SerializedName("back_shiny_female")
        val backShinyFemale: Any?, // null
        @SerializedName("front_default")
        val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
        @SerializedName("front_female")
        val frontFemale: Any?, // null
        @SerializedName("front_shiny")
        val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png
        @SerializedName("front_shiny_female")
        val frontShinyFemale: Any?, // null
        @SerializedName("other")
        val other: Other,
        @SerializedName("versions")
        val versions: Versions
    ) {
        data class Other(
            @SerializedName("dream_world")
            val dreamWorld: DreamWorld,
            @SerializedName("official-artwork")
            val officialArtwork: OfficialArtwork
        ) {
            data class DreamWorld(
                @SerializedName("front_default")
                val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg
                @SerializedName("front_female")
                val frontFemale: Any? // null
            )

            data class OfficialArtwork(
                @SerializedName("front_default")
                val frontDefault: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png
            )
        }

        data class Versions(
            @SerializedName("generation-i")
            val generationI: GenerationI,
            @SerializedName("generation-ii")
            val generationIi: GenerationIi,
            @SerializedName("generation-iii")
            val generationIii: GenerationIii,
            @SerializedName("generation-iv")
            val generationIv: GenerationIv,
            @SerializedName("generation-v")
            val generationV: GenerationV,
            @SerializedName("generation-vi")
            val generationVi: GenerationVi,
            @SerializedName("generation-vii")
            val generationVii: GenerationVii,
            @SerializedName("generation-viii")
            val generationViii: GenerationViii
        ) {
            data class GenerationI(
                @SerializedName("red-blue")
                val redBlue: RedBlue,
                @SerializedName("yellow")
                val yellow: Yellow
            ) {
                data class RedBlue(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/1.png
                    @SerializedName("back_gray")
                    val backGray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/gray/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/1.png
                    @SerializedName("front_gray")
                    val frontGray: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/gray/1.png
                )

                data class Yellow(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/1.png
                    @SerializedName("back_gray")
                    val backGray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/gray/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/1.png
                    @SerializedName("front_gray")
                    val frontGray: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/gray/1.png
                )
            }

            data class GenerationIi(
                @SerializedName("crystal")
                val crystal: Crystal,
                @SerializedName("gold")
                val gold: Gold,
                @SerializedName("silver")
                val silver: Silver
            ) {
                data class Crystal(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/1.png
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/shiny/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/1.png
                    @SerializedName("front_shiny")
                    val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/shiny/1.png
                )

                data class Gold(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/1.png
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/shiny/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/1.png
                    @SerializedName("front_shiny")
                    val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/shiny/1.png
                )

                data class Silver(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/1.png
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/shiny/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/1.png
                    @SerializedName("front_shiny")
                    val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/1.png
                )
            }

            data class GenerationIii(
                @SerializedName("emerald")
                val emerald: Emerald,
                @SerializedName("firered-leafgreen")
                val fireredLeafgreen: FireredLeafgreen,
                @SerializedName("ruby-sapphire")
                val rubySapphire: RubySapphire
            ) {
                data class Emerald(
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/1.png
                    @SerializedName("front_shiny")
                    val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/1.png
                )

                data class FireredLeafgreen(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/1.png
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/shiny/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/1.png
                    @SerializedName("front_shiny")
                    val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/shiny/1.png
                )

                data class RubySapphire(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/1.png
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/shiny/1.png
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/1.png
                    @SerializedName("front_shiny")
                    val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/shiny/1.png
                )
            }

            data class GenerationIv(
                @SerializedName("diamond-pearl")
                val diamondPearl: DiamondPearl,
                @SerializedName("heartgold-soulsilver")
                val heartgoldSoulsilver: HeartgoldSoulsilver,
                @SerializedName("platinum")
                val platinum: Platinum
            ) {
                data class DiamondPearl(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/1.png
                    @SerializedName("back_female")
                    val backFemale: Any?, // null
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/shiny/1.png
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?, // null
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                )

                data class HeartgoldSoulsilver(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/1.png
                    @SerializedName("back_female")
                    val backFemale: Any?, // null
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/shiny/1.png
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?, // null
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                )

                data class Platinum(
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/1.png
                    @SerializedName("back_female")
                    val backFemale: Any?, // null
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/shiny/1.png
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?, // null
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                )
            }

            data class GenerationV(
                @SerializedName("black-white")
                val blackWhite: BlackWhite
            ) {
                data class BlackWhite(
                    @SerializedName("animated")
                    val animated: Animated,
                    @SerializedName("back_default")
                    val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/1.png
                    @SerializedName("back_female")
                    val backFemale: Any?, // null
                    @SerializedName("back_shiny")
                    val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/1.png
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any?, // null
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                ) {
                    data class Animated(
                        @SerializedName("back_default")
                        val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/1.gif
                        @SerializedName("back_female")
                        val backFemale: Any?, // null
                        @SerializedName("back_shiny")
                        val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/1.gif
                        @SerializedName("back_shiny_female")
                        val backShinyFemale: Any?, // null
                        @SerializedName("front_default")
                        val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/1.gif
                        @SerializedName("front_female")
                        val frontFemale: Any?, // null
                        @SerializedName("front_shiny")
                        val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/1.gif
                        @SerializedName("front_shiny_female")
                        val frontShinyFemale: Any? // null
                    )
                }
            }

            data class GenerationVi(
                @SerializedName("omegaruby-alphasapphire")
                val omegarubyAlphasapphire: OmegarubyAlphasapphire,
                @SerializedName("x-y")
                val xY: XY
            ) {
                data class OmegarubyAlphasapphire(
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                )

                data class XY(
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                )
            }

            data class GenerationVii(
                @SerializedName("icons")
                val icons: Icons,
                @SerializedName("ultra-sun-ultra-moon")
                val ultraSunUltraMoon: UltraSunUltraMoon
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any? // null
                )

                data class UltraSunUltraMoon(
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any?, // null
                    @SerializedName("front_shiny")
                    val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/shiny/1.png
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? // null
                )
            }

            data class GenerationViii(
                @SerializedName("icons")
                val icons: Icons
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/1.png
                    @SerializedName("front_female")
                    val frontFemale: Any? // null
                )
            }
        }
    }

    data class Stat( // !!!!!
        @SerializedName("base_stat")
        val baseStat: Int, // 45
        @SerializedName("effort")
        val effort: Int, // 0
        @SerializedName("stat")
        val stat: Stat
    ) {
        data class Stat(
            @SerializedName("name")
            val name: String, // hp
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/stat/1/
        )
    }

    data class Type(
        @SerializedName("slot")
        val slot: Int, // 1 - order of showing
        @SerializedName("type")
        val type: Type
    ) : Serializable {
        data class Type(
            @SerializedName("name")
            val name: String, // grass
            @SerializedName("url")
            val url: String // https://pokeapi.co/api/v2/type/12/
        ) : Serializable
    }

    fun getFormattedId(): String {
        return if (id.toString().length >= 3) {
            id.toString()
        } else {
            "0".repeat(3 - id.toString().length) + id
        }
    }

    fun getAvatarUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }


}