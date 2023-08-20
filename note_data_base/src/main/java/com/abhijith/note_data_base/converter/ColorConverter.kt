package com.abhijith.note_data_base.converter

import androidx.room.TypeConverter
import com.abhijith.note_data_base.models.NoteColor

class ColorConverter {
    @TypeConverter
    fun fromString(color:String): NoteColor {
        return NoteColor(color)
    }

    @TypeConverter
    fun toString(noteColor: NoteColor): String {
        return noteColor.color
    }
}