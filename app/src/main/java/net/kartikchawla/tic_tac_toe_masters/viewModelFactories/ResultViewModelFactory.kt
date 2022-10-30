package net.kartikchawla.tic_tac_toe_masters.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.kartikchawla.tic_tac_toe_masters.viewModels.ResultViewModel

class ResultViewModelFactory(private val finalResult: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java))
            return ResultViewModel(finalResult) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}


