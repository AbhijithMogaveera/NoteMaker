package com.abhijith.note_data_base.repo

import com.abhijith.note_data_base.dao.NotesDao
import com.abhijith.note_data_base.models.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepo
@Inject constructor(
    val notesDao: NotesDao
) {
    suspend fun insertNote(
        note: Note
    ){
        notesDao.insertNote(note)
    }
}