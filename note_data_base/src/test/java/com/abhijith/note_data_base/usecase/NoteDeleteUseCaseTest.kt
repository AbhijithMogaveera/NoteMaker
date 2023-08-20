package com.abhijith.note_data_base.usecase

import com.abhijith.note_data_base.repo.NotesRepo
import com.abhijith.note_data_base.repo.NotesRepoDefaultImpl
import com.abhijith.note_data_base.repo.dao.FakeNotesDao
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Test


internal class NoteDeleteUseCaseTest {

    @Test
    fun deleteNote(): Unit = runBlocking {
        val deleteNote = NoteDeleteUseCase(NotesRepoDefaultImpl(FakeNotesDao()))
            .deleteNote(FakeNotesDao.NOTE_ID_EXIST)
            .catch {
                assert(false)
            }.first().apply {
                assert(true)
            }
    }

    @Test
    fun deleteNonExistingNote(): Unit = runBlocking {
        val deleteNote = NoteDeleteUseCase(NotesRepoDefaultImpl(FakeNotesDao()))
            .deleteNote(FakeNotesDao.NOTE_ID_NOT_EXIST)
            .catch {
                assert(true)
            }.firstOrNull().apply {
                assert(true)
            }
    }
}