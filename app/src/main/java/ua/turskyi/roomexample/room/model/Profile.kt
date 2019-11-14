package ua.turskyi.roomexample.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.turskyi.roomexample.room.model.Profile.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Profile(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int,
    @ColumnInfo(name = COLUMN_NAME) var name: String?,
    @ColumnInfo(name = COLUMN_AGE) var age: String?,
    @ColumnInfo(name = COLUMN_LANGUAGE) var language: String?
) {
    constructor(name: String?,age: String?) : this(0,name,age,null)

    companion object {
        const val TABLE_NAME = "profile"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_LANGUAGE = "language"
    }
}