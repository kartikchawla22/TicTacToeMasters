package net.kartikchawla.tic_tac_toe_masters.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Games::class], version = 1, exportSchema = false)
abstract class GameDataBase: RoomDatabase() {
    abstract val gameDao: Game_DAO

    companion object {
        @Volatile
        private var INSTANCE: GameDataBase? = null

        fun getInstance(context: Context): GameDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, GameDataBase::class.java, "Game_DataBase").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}