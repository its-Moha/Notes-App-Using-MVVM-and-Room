package com.example.notesapp


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notesTable")
class Notes(


    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timeStamp") val timeStamp: String,

    ) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}