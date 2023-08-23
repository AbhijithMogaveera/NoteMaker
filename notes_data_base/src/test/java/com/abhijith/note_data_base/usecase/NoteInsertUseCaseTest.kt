package com.abhijith.note_data_base.usecase

import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.repo.NotesRepoDefaultImpl
import com.abhijith.note_data_base.repo.dao.FakeNotesDao
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class NoteInsertUseCaseTest {

    @Test
    fun insertNote():Unit = runBlocking {
        val useCase = NoteInsertUseCase(NotesRepoDefaultImpl(FakeNotesDao()))
        useCase.insertNote(
            title = "",
            description = "",
            color = NoteColor.getColor().random()
        ).first()
    }
}