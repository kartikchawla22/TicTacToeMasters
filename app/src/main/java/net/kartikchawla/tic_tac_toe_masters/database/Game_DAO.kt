package net.kartikchawla.tic_tac_toe_masters.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Game_DAO {
    @Insert
    suspend fun insert(games: Games)

    @Update
    suspend fun update(games: Games)

    @Delete
    suspend fun delete(games: Games)

    @Query("SElECT * FROM games_table WHERE gameID = :gameId")
    fun get(gameId: Long): LiveData<Games>

    @Query("SElECT * FROM games_table ORDER BY gameID DESC")
    fun getAll(): LiveData<List<Games>>
}