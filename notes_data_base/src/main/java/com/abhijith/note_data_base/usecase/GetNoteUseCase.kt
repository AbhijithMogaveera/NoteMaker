package com.abhijith.note_data_base.usecase

import arrow.core.computations.ResultEffect.bind
import com.abhijith.note_data_base.repo.NotesRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repo: NotesRepo
) {
    fun getNoteById(noteId: Long) = flow {
        emit(repo.getNoteById(noteId).bind())
    }

    suspend fun getAllNotes() = repo.getAllNotes()

    suspend fun getAllNotesUpdatesInFlow() = repo.getAllNotesUpdatesInFlow()
}