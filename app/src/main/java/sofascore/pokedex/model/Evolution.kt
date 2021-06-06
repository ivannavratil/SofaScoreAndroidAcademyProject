package sofascore.pokedex.model

import sofascore.pokedex.model.db.DetailPokemonResponse

class Evolution(
     val id: Int,
     val name: String,
     val types: List<DetailPokemonResponse.Type>,
     val minLevel: Int?,
    ) {
}