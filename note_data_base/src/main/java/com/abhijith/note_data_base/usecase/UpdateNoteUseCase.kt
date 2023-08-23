package com.abhijith.note_data_base.usecase

import arrow.core.computations.ResultEffect.bind
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.repo.NotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UpdateNoteUseCase @Inject constructor(
    private val repo: NotesRepo
) {
    suspend fun updateNote(
        note: Note,
        deleteIfBlank:Boolean
    ): Flow<Unit> = flow {
        if(note.title.isBlank() && note.description.isBlank() && deleteIfBlank)
            repo.deleteNote(note).bind()
        else
            repo.updateNote(note).bind()
        emit(Unit)

    }
}
