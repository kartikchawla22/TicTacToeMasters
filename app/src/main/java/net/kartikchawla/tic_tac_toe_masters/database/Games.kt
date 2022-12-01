package net.kartikchawla.tic_tac_toe_masters.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games_table")
data class Games (
    @PrimaryKey(autoGenerate = true)
    val gameID: Long = 0L,
    @ColumnInfo(name = "who_won")
    var whoWon: String = "",
    @ColumnInfo(name = "date")
    var gameDataTime: String = "",
    @ColumnInfo(name = "order_of_moves")
    var gameOrderOfMoves: String = "",
    @ColumnInfo(name = "moves")
    var gameMoves: String = ""
    )
