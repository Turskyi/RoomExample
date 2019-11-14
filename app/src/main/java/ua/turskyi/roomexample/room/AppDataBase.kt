package ua.turskyi.roomexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.turskyi.roomexample.room.dao.ProfileDAO
import ua.turskyi.roomexample.room.model.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun profileDAO():ProfileDAO

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(AppDataBase::class) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(context, AppDataBase::class.java, "App.DB").build()
                }
            }
            return INSTANCE!!
        }
    }
}