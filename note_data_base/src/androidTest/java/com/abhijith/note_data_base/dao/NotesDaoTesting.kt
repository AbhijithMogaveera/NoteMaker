package com.abhijith.note_data_base.dao

import com.abhijith.note_data_base.models.Note
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class NotesDaoTesting {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun noteInsertionTest() = runBlocking {
        notesDao.insertNote(
            Note(
                title = "",
                description = ""
            )
        )
        assert(notesDao.getAllNotes().first().size == 1)
    }

    @Test
    fun noteDeletionTest() = runBlocking {
        notesDao.insertNote(
            Note(
                title = "",
                description = ""
            )
        )
        val note = notesDao.getAllNotes().first().first()
        notesDao.delete(note = note)
        assert(notesDao.getAllNotes().first().isEmpty())
    }

    @Test
    fun noteUpdateTest() = runBlocking {
        notesDao.insertNote(
            Note(
                title = "old title",
                description = "old description"
            )
        )
        val newTitle = "New title"
        val note = notesDao.getAllNotes().first().first().copy(title = newTitle)
        assert(notesDao.update(note = note) != -1)
        assert(notesDao.getAllNotes().first().size == 1)
        assert(notesDao.getAllNotes().first().first().title == newTitle) {
            notesDao.getAllNotes().first()
        }
    }

    @Test
    fun getAllNoteTest() = runBlocking {
        notesDao.insertNote(
            Note(
                title = "Note 1",
                description = "Description One"
            )
        )
        notesDao.insertNote(
            Note(
                title = "Note 2",
                description = "Description Two"
            )
        )
        assert(notesDao.getAllNotes().first().size == 2)
    }

    @Test
    fun addTagToNote() = runBlocking {

    }

    @After
    fun tearDown() {

    }
}

