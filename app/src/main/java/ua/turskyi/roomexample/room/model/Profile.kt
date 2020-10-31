package ua.turskyi.roomexample.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ua.turskyi.roomexample.room.model.Profile.Companion.COLUMN_ID
import ua.turskyi.roomexample.room.model.Profile.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME, indices = [Index(value = [COLUMN_ID], unique = true)])
data class Profile(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = COLUMN_ID) var id: Long,
    @ColumnInfo(name = COLUMN_NAME) var name: String?,
    @ColumnInfo(name = COLUMN_AGE) var age: String?,
    @ColumnInfo(name = COLUMN_LANGUAGE) var language: String?,

    /* in case of migration */
//    val birthday: Long
) {
    constructor(name: String?,age: String?) : this(0,name,age,null)
    constructor() : this(0, null, null, null)

    companion object {
        const val TABLE_NAME = "profile"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_LANGUAGE = "language"
    }
}