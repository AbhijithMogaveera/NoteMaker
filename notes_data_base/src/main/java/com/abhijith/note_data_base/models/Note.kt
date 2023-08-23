package com.abhijith.note_data_base.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var note_id:Long = 0L,
    var title:String,
    var description:String,
    var color:NoteColor
)