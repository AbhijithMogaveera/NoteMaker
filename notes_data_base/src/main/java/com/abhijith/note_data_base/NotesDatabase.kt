package com.abhijith.note_data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.abhijith.note_data_base.converter.ColorConverter
import com.abhijith.note_data_base.dao.NotesDao
import com.abhijith.note_data_base.dao.TagsDao
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteToTags
import com.abhijith.note_data_base.models.Tag

@Database(
    entities = [Note::class, Tag::class, NoteToTags::class],
    version = 1
)
@TypeConverters(
    value = [ColorConverter::class]
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getUserDao():NotesDao
    abstract fun getTagsDao():TagsDao
}