package com.abhijith.note_data_base.models

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "NoteToTags",
    primaryKeys = ["note_id","tag_id"],
    foreignKeys = [
         ForeignKey(
            entity = Note::class,
            parentColumns = ["note_id"],
            childColumns = ["note_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["tag_id"],
            childColumns = ["tag_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
data class NoteToTags(
    val note_id:Long,
    val tag_id:Long,
)