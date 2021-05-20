package sofascore.pokedex.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String,

) :Serializable{
    var favourite: Boolean = false
}