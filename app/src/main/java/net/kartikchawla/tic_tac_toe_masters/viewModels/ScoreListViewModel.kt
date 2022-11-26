package net.kartikchawla.tic_tac_toe_masters.viewModels

import androidx.lifecycle.ViewModel
import net.kartikchawla.tic_tac_toe_masters.database.Game_DAO

class ScoreListViewModel(val dao: Game_DAO): ViewModel(){
    val scores = dao.getAll()

}