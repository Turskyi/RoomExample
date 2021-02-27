package ua.turskyi.roomexample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.turskyi.roomexample.room.dao.ProfileDAO
import ua.turskyi.roomexample.room.model.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        /* in case of migration */
//        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE profile ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL")
//            }
//        }
//
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class) {
                INSTANCE ?: run {
                    INSTANCE =
                        Room.databaseBuilder(context, AppDatabase::class.java, "App.DB")
                            /* in case of migration */
//                            .addMigrations(MIGRATION_1_2)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
    abstract fun profileDAO():ProfileDAO
}