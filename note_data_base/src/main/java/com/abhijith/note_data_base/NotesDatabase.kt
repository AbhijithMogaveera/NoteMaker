package com.abhijith.note_data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhijith.note_data_base.dao.NotesDao
import com.abhijith.note_data_base.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun getUserDao():NotesDao
}