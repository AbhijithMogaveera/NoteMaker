package com.abhijith.note_data_base.usecase

import arrow.core.computations.ResultEffect.bind
import com.abhijith.note_data_base.exceptions.EmptyResource
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.repo.NotesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NoteInsertUseCase
@Inject constructor(
    private val repo: NotesRepo
) {
    fun insertNote(
        title: String,
        description: String,
        color: NoteColor
    ): Flow<Note> = flow {
        if(title.isBlank() && description.isBlank())
            throw EmptyResource
        val id = repo.insertNote(
            note = Note(title = title, description = description, color = color)
        ).bind()
        emit(repo.getNoteById(id).bind())
    }
}
