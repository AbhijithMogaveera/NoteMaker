package com.abhijith.note_data_base.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(
    var title:String,
    var description:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L
}