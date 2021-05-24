package sofascore.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import sofascore.pokedex.Util
import java.io.Serializable

@Entity
class Pokemon(pokemonNamePhoto: PokemonNamePhoto, var favourite: Boolean) : Serializable {
    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    var id: Int = Util.getId(pokemonNamePhoto.url)

    @ColumnInfo(name = "pokemon_name")
    var name: String = pokemonNamePhoto.name

    @ColumnInfo(name = "pokemon_url")
    var url: String = pokemonNamePhoto.url

    constructor() : this(PokemonNamePhoto("", ""), false)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pokemon

        if (favourite != other.favourite) return false
        if (id != other.id) return false
        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = favourite.hashCode()
        result = 31 * result + id
        result = 31 * result + name.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }


}

