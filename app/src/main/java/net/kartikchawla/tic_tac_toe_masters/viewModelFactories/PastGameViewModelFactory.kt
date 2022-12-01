package net.kartikchawla.tic_tac_toe_masters.viewModelFactories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.kartikchawla.tic_tac_toe_masters.database.Game_DAO
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import net.kartikchawla.tic_tac_toe_masters.viewModels.PastGameViewModel
import net.kartikchawla.tic_tac_toe_masters.viewModels.ScoreListViewModel

class PastGameViewModelFactory(private val gameId: Long, private val dao: Game_DAO): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PastGameViewModel::class.java)) {
            return PastGameViewModel(gameId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}