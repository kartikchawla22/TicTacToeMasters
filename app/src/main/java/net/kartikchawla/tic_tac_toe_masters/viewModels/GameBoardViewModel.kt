package net.kartikchawla.tic_tac_toe_masters.viewModels

import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.system.exitProcess

class GameBoardViewModel: ViewModel() {
    private val _gameOver = MutableLiveData<Boolean>(false)  // How the word is displayed
    val gameOver: LiveData<Boolean> get() = _gameOver


    private var movesPlayed = Array(10) {i -> ""}

    private val _whoseTurn = MutableLiveData<String>(Constants.X)  // How the word is displayed
    val whoseTurn: LiveData<String> get() = _whoseTurn


    private var _gameState = MutableLiveData<String>( "X's Turn")
    val gameState get() = _gameState

    private var whoWon = ""

    private var lastPlayed = ""

    private var totalMoves = 0

    private var orderOfMoves = ""


    private val winningCombinations: Array<IntArray> = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6),
        intArrayOf(7,8,9),
        intArrayOf(1,4,7),
        intArrayOf(2,5,8),
        intArrayOf(3,6,9),
        intArrayOf(1,5,9),
        intArrayOf(3,5,7)
    )

    fun gridButtonClickHandler(num: Int): String {
        orderOfMoves += num.toString() + ","
        totalMoves++
        val currentTurn = whoseTurn.value!!
        lastPlayed = currentTurn
        movesPlayed[num] = whoseTurn.value!!
        if(whoseTurn.value == Constants.X) {
            _whoseTurn.value = Constants.O
        } else {
            _whoseTurn.value = Constants.X
        }
        return currentTurn
    }


    fun squareTouchedHandler(square: View, id: Int): String {
        val buttonText = (square as Button).text
        var movePlayed = ""
        if(buttonText == "" && !gameOver.value!!) {
            movePlayed = gridButtonClickHandler(id)
            gameState.value = whoseTurn.value + "'s Turn"
        }
        isGameFinished()
        if(gameOver.value!!) {
            if(whoWon.length > 0) {
                gameState.value = whoWon + " Won!!"
            } else {
                gameState.value = "Draw!"
            }
        }
        return movePlayed
    }


    fun isGameFinished() {
        if(totalMoves < 5) {
            _gameOver.value = false
            return
        }

        for (item in winningCombinations) {
            val positions = item as IntArray
            if(movesPlayed[positions[0]] == lastPlayed &&
                movesPlayed[positions[1]] == lastPlayed &&
                movesPlayed[positions[2]] == lastPlayed) {
                whoWon = lastPlayed
                setEndGameMessage()
                _gameOver.value = true
                return
            }
        }
        _gameOver.value = totalMoves == 9
        setEndGameMessage()
        return
    }
    private fun setEndGameMessage() {
        if(whoWon.length > 0) {
            gameState.value = whoWon + " Won!!"
        } else {
            gameState.value = "Draw!"
        }
    }
    fun saveGame() {

    }
    class Constants {
        companion object {
            val X = "X"
            val O = "O"
        }
    }

}