package com.abhijith.note_data_base.usecase

import arrow.core.computations.ResultEffect.bind
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.repo.NotesRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteGetUserCase @Inject constructor(
    private val repo: NotesRepo
) {
    fun getNoteById(noteId: Long) = flow<Note> {
        repo.getNoteById(noteId).bind()
    }

    suspend fun getAllNotes() = repo.getAllNotes()

    suspend fun getAllNotesUpdatesInFlow() = repo.getAllNotesUpdatesInFlow()
}