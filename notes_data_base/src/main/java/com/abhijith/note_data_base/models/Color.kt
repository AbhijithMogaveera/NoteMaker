package com.abhijith.note_data_base.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class NoteColor private constructor(var color: String):Parcelable {
    companion object{
        operator fun invoke(color: String): NoteColor {
            return getColor().first {
                it.color == color
            }
        }
        fun getColor():List<NoteColor> = listOf(
            "#fffbed",
            "#5bc9ff",
            "#ff6666",
            "#ffd46c",
            "#67ffc5",
            "#000000"
        ).map {
            NoteColor(it)
        }
    }

}

fun String.toNoteColor() = NoteColor.getColor().first { it.color == this }