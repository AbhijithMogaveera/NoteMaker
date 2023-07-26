package com.abhijith.note_data_base.repo

import arrow.core.Either
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.repo.dao.FakeNotesDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class NotesRepoDefaultImplTest {

    private lateinit var repo: NotesRepo

    @Before
    fun setUp() {
        repo = NotesRepoDefaultImpl(FakeNotesDao())
    }

    @After
    fun tearDown() {

    }

    //-----------GET
    @Test
    fun getNonExistingNoteById() = runBlocking {
        val note2 = repo.getNoteById(
            id = FakeNotesDao.NOTE_ID_NOT_EXIST
        )
        assert(note2 is Either.Left)
    }

    @Test
    fun getExistingNoteById() = runBlocking {
        val note = repo.getNoteById(
            id = FakeNotesDao.NOTE_ID_EXIST
        )
        assert(note is Either.Right)
    }

    //-----------INSERT
    @Test
    fun insertNote() = runBlocking {
        val note = repo.insertNote(FakeNotesDao.NoteTrue)
        assert(note is Either.Right && note.value == FakeNotesDao.NoteTrue.note_id)
    }

    @Test
    fun insertBlankNote() = runBlocking {
        val note = repo.insertNote(
            Note(
                title = "",
                description = "",
                color = ""
            )
        )
        assert(note is Either.Left)
    }

    //-----------DELETE
    @Test
    fun deleteNoteById() = runBlocking {
        val note = repo.deleteNoteById(
            FakeNotesDao.NoteTrue.note_id
        )
        assert(note is Either.Right)
    }

    @Test
    fun deleteNote() = runBlocking {
        val note = repo.deleteNote(
            FakeNotesDao.NoteTrue
        )
        assert(note is Either.Right)
    }

    @Test
    fun deleteNoteNonExistingNoteById() = runBlocking {
        val note = repo.deleteNoteById(
            FakeNotesDao.NOTE_ID_NOT_EXIST
        )
        assert(note is Either.Left)
    }

    @Test
    fun deleteNoteNonExistingNote() = runBlocking {
        val note = repo.deleteNoteById(
            FakeNotesDao.NOTE_ID_NOT_EXIST
        )
        assert(note is Either.Left)
    }

    //----UPDATE
    @Test
    fun updateNote(): Unit = runBlocking {
        val note = repo.updateNote(FakeNotesDao.NoteTrue)
        assert(note is Either.Right)
    }

    @Test
    fun updateNonExistingNote(): Unit = runBlocking {
        val note = repo.updateNote(
            Note(
                note_id = 10L,
                title = "",
                description = "sfsdf",
                color = "#ffffff"
            )
        )
        assert(note is Either.Left)
    }

}

