package com.abhijith.note_data_base.usecase

import arrow.core.computations.ResultEffect.bind
import com.abhijith.note_data_base.exceptions.EmptyResource
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.repo.NotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NoteDeleteUseCase
@Inject constructor(
    private val repo: NotesRepo
) {
    suspend fun  deleteNote(
        noteId: Long
    ): Flow<Unit> = flow {
        emit(repo.deleteNoteById(noteId).bind())
    }
}
