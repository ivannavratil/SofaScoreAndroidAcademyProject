package sofascore.pokedex.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "pokemon_id") var id: Int,
    @ColumnInfo(name = "pokemon_name") var name: String,
    @ColumnInfo(name = "pokemon_url") var url: String,
    var favourite: Boolean
) : Serializable {




}

