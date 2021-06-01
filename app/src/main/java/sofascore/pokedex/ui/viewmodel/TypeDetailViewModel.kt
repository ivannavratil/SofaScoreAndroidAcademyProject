package sofascore.pokedex.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sofascore.pokedex.model.TypeDetailMoveResponse
import sofascore.pokedex.model.TypeDetailResponse
import sofascore.pokedex.model.network.Network

class TypeDetailViewModel : ViewModel() {

    var typeDetail: MutableLiveData<TypeDetailResponse> = MutableLiveData()
    var moveDetail: MutableLiveData<TypeDetailMoveResponse> = MutableLiveData()

    private val network = Network().service

    fun getDetailedPokemonInfo(id: Int, context: Context) {

        viewModelScope.launch {

            lateinit var res1: TypeDetailResponse
            lateinit var res2: TypeDetailMoveResponse

            val async1 = async { res1 = network.getTypeDetail(id) }
            val async2 = async { res2 = network.getMoveDetail(id) }

            async1.await()
            typeDetail.value = res1

            async2.await()
            moveDetail.value = res2
        }
    }
}