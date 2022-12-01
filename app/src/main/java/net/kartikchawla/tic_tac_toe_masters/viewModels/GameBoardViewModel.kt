package net.kartikchawla.tic_tac_toe_masters.viewModels

import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kartikchawla.tic_tac_toe_masters.database.Game_DAO
import net.kartikchawla.tic_tac_toe_masters.database.Games
import java.text.SimpleDateFormat
import java.util.*

class GameBoardViewModel(private val gameDao: Game_DAO): ViewModel() {
    private val _gameOver = MutableLiveData<Boolean>(false)  // How the word is displayed
    val gameOver: LiveData<Boolean> get() = _gameOver


    private var movesPlayed = Array(10) {i -> ""}

    private val _whoseTurn = MutableLiveData<String>(Constants.X)  // How the word is displayed
    val whoseTurn: LiveData<String> get() = _whoseTurn


    private var _gameState = MutableLiveData<String>( "X's Turn")
    val gameState get() = _gameState

    private var _result = MutableLiveData<String>( "")
    val result get() = _result

    private var _gameBoardButtons = MutableLiveData<List<String>>(mutableListOf("", "", "","", "", "","", "", "", ""))
    val gameBoardButtons get() = _gameBoardButtons

    private var gameBoardButtonsCopy = mutableListOf("", "", "","", "", "","", "", "", "")

    private var gameBoardMovementsCopy = mutableListOf("-", "-", "-","-", "-", "-","-", "-", "-", "-")

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
        println(gameBoardButtons.value)
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
            square.text = movePlayed
            gameBoardMovementsCopy[id] =movePlayed
            gameBoardButtonsCopy[id] = movePlayed;
            gameBoardButtons.value = gameBoardButtonsCopy
            println(gameBoardButtons.value)
            println(gameOver.value)
            gameState.value = whoseTurn.value + "'s Turn"
        }
        isGameFinished()
        return movePlayed
    }


    fun isGameFinished() {
        if(totalMoves < 5) {
            _gameOver.value = false
            return
        }

        for (item in winningCombinations) {
            val positions = item as IntArray
            println(movesPlayed[positions[0]])
            println(movesPlayed[positions[1]])
            println(movesPlayed[positions[2]])
            println(lastPlayed)
            if(movesPlayed[positions[0]] == lastPlayed &&
                movesPlayed[positions[1]] == lastPlayed &&
                movesPlayed[positions[2]] == lastPlayed) {
                whoWon = lastPlayed
                setEndGameMessage(true)
                return
            }
        }
        whoWon = ""
        println("totalMoves")
        println(totalMoves)
        setEndGameMessage(totalMoves == 9)
        return
    }
    private fun setEndGameMessage(gameOverValue: Boolean = false) {
            if(whoWon.isNotEmpty()) {
                whoWon = whoWon + " Won!!"
                result.value = whoWon
            } else {
                whoWon = "Draw!"
                result.value = whoWon
        }
        _gameOver.value = gameOverValue
        println("result.value")
        println(result.value)
    }
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun saveGame() {
        viewModelScope.launch {
            val newGame = Games()
            val date = getCurrentDateTime()
            val dateInString = date.toString("dd/MM/yyyy HH:mm:ss")
            println(dateInString)
            newGame.whoWon = whoWon
            newGame.gameOrderOfMoves = orderOfMoves
            newGame.gameDataTime = dateInString
            newGame.gameMoves = gameBoardMovementsCopy.toString()
            gameDao.insert(newGame)
        }
    }
    class Constants {
        companion object {
            const val X = "X"
            const val O = "O"
        }
    }

}