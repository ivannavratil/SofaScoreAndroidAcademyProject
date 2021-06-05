package sofascore.pokedex.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import sofascore.pokedex.model.TypeDetailMoveResponse
import sofascore.pokedex.model.TypeDetailResponse
import sofascore.pokedex.model.network.Network
import sofascore.pokedex.other.Util

class TypeDetailViewModel : ViewModel() {

    var typeDetail: MutableLiveData<TypeDetailResponse> = MutableLiveData()
    var moveDetail: MutableLiveData<List<TypeDetailMoveResponse>> = MutableLiveData()

    private val network = Network().service

    fun getDetailTypeAndMove(id: Int, context: Context) {

        viewModelScope.launch {

            typeDetail.value = network.getTypeDetail(id)

            val asyncTasks: List<Deferred<TypeDetailMoveResponse>> =
                typeDetail.value!!.moves.map { move -> async { network.getMoveDetail(Util.getId(move.url)) } }

            val responses: List<TypeDetailMoveResponse> = asyncTasks.awaitAll()

            moveDetail.value = responses


        }
    }
}