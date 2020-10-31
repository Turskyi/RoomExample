package ua.turskyi.roomexample.room.dao

import androidx.room.*
import ua.turskyi.roomexample.room.model.Profile

@Dao
interface ProfileDAO {

    @Update
    fun update(profile: Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Query("SELECT * FROM ${Profile.TABLE_NAME} LIMIT 1")
    fun getAll(): List<Profile>


    // examples:
    @Delete
    fun delete(profile: Profile)

    @Query("DELETE FROM ${Profile.TABLE_NAME} /* WHERE ${Profile.COLUMN_AGE} < 18*/")
    fun deleteAll()
}
