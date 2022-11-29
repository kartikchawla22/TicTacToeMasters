package net.kartikchawla.tic_tac_toe_masters.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.kartikchawla.tic_tac_toe_masters.database.Game_DAO
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

class ScoreListViewModel(val dao: Game_DAO): ViewModel(){
    val scores = dao.getAll()

    private val _navigateToPastGame = MutableLiveData<Long?>()
    val navigateToPastGame: LiveData<Long?> get() = _navigateToPastGame

    fun onGameClicked(gameId: Long){
        _navigateToPastGame.value = gameId
    }

    fun onGameNavigated(){
        _navigateToPastGame.value = null
    }
}