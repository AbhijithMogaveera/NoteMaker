package com.abhijith.note_data_base.repo.dao

import com.abhijith.note_data_base.dao.NotesDao
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.toNoteColor
import kotlinx.coroutines.flow.Flow

class FakeNotesDao : NotesDao {

    override suspend fun insertNote(note: Note): Long {
        return note.note_id
    }

    override suspend fun update(note: Note): Int {
        return if(note == NoteTrue)
             1
        else
            0
    }

    override suspend fun delete(note: Note): Int {
        return if(note == NoteTrue)
            1
        else
            0
    }

    override suspend fun delete(id: Long): Int {
        return if(id == NOTE_ID_EXIST)
            1
        else
            0
    }

    override fun getAllNotesAsFlow(): Flow<List<Note>> {
        TODO()
    }

    override suspend fun getAllNotes(): List<Note> {
        return listOf(NoteTrue)
    }

    override suspend fun getNoteById(id: Long): Note? {
        return if (id == NOTE_ID_EXIST)
            NoteTrue
        else null
    }


    companion object {
        const val NOTE_ID_EXIST = 100L
        const val NOTE_ID_NOT_EXIST = 200L

        val NoteTrue = Note(
            note_id = NOTE_ID_EXIST,
            title = "Dunmmy title",
            description = "Dummy description",
            color = "#5bc9ff".toNoteColor()
        )
    }
}