package net.kartikchawla.tic_tac_toe_masters.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.kartikchawla.tic_tac_toe_masters.database.Game_DAO
import net.kartikchawla.tic_tac_toe_masters.viewModels.ScoreListViewModel

class ScoreListViewModelFactory(private val dao: Game_DAO): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ScoreListViewModel::class.java)) {
            return ScoreListViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}