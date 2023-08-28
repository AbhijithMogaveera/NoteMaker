package com.abhijith.note_data_base.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var note_id:Long = 0L,
    var title:String,
    var description:String,
    var color:NoteColor
):Parcelable