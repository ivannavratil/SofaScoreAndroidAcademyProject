package sofascore.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "p_id") var id: Int,
    @ColumnInfo(name = "p_name") var name: String,
    @ColumnInfo(name = "p_favourite") var favourite: Boolean,
    @ColumnInfo(name = "p_favourite_number") var favouriteNumber: Int?
) : Serializable {
    fun getFormattedId(): String {
        return "0".repeat(3 - id.toString().length) + id
    }

    fun getAvatarUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }

    companion object {
        fun getAvatarUrl(id: Int): String {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        }
    }

}

