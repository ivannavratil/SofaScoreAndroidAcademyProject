package sofascore.pokedex.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Pokemon(
    @PrimaryKey @ColumnInfo(name = "pokemon_id")
    val id: Int,
    @ColumnInfo(name = "pokemon_name")
    val name: String,
    @ColumnInfo(name = "pokemon_url")
    val url: String
) : Serializable {
    var favourite: Boolean = false
}