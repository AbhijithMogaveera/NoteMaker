package com.abhijith.note_data_base.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class NoteWithTags(
    @Embedded
    val note: Note,

    @Relation(parentColumn = "note_id", entityColumn = "tag_id", associateBy = Junction(NoteToTags::class))
    val tags: List<Tag>
)