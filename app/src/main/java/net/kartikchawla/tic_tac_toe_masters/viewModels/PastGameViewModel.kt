package net.kartikchawla.tic_tac_toe_masters.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kartikchawla.tic_tac_toe_masters.database.Game_DAO

class PastGameViewModel(gameId: Long, val dao: Game_DAO): ViewModel() {
    val game = dao.get(gameId)

    private var _pastGame = MutableLiveData<List<String>>()
    val gameBoardTextViews : LiveData<List<String>> get() = _pastGame

    fun setMovements(movements: String){
        viewModelScope.launch {
            var movementsCopy = movements
            movementsCopy = movementsCopy.replace(" ","")
            movementsCopy = movementsCopy.replace("[","")
            movementsCopy = movementsCopy.replace("]","")
            movementsCopy = movementsCopy.replace(",","")
            movementsCopy = movementsCopy.replace("-"," ")

            var list: List<String> = movementsCopy.map { it.toString() }
            _pastGame.value = list
        }
    }
}