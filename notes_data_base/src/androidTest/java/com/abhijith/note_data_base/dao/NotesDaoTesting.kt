package com.abhijith.note_data_base.dao

import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.toNoteColor
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
                description = "",
                color = "#ffd46c".toNoteColor()
            )
        )
        assert(notesDao.getAllNotesAsFlow().first().size == 1)
    }

    @Test
    fun existingNoteDeletionTest() = runBlocking {
        notesDao.insertNote(
            Note(
                title = "",
                description = "",
                color = "#ffd46c".toNoteColor()
            )
        )
        val note = notesDao.getAllNotesAsFlow().first().first()
        val value = notesDao.delete(note = note)
        assert(value == 1)
    }

    @Test
    fun nonExistingNoteDeletionTest() = runBlocking {
        val value = notesDao.delete(
            note = Note(
                title = "",
                description = "",
                color = "#ffd46c".toNoteColor()
            )
        )
        assert(value == 0)
    }

    @Test
    fun noteUpdateTest() = runBlocking {
        val noteId:Long = notesDao.insertNote(
            Note(
                title = "old title",
                description = "old description",
                color = "#ffd46c".toNoteColor()
            )
        )
        val newTitle = "New title"
        val note = notesDao.getAllNotesAsFlow().first().first().copy(title = newTitle)
        assert(notesDao.update(note = note) == 1)
        assert(notesDao.getAllNotesAsFlow().first().size == 1)
        assert(notesDao.getAllNotesAsFlow().first().first().note_id == noteId) {
            notesDao.getAllNotesAsFlow().first()
        }
    }

    @Test
    fun getAllNoteTest() = runBlocking {
        notesDao.insertNote(
            Note(
                title = "Note 1",
                description = "Description One",
                color = "#ffd46c".toNoteColor()
            )
        )
        notesDao.insertNote(
            Note(
                title = "Note 2",
                description = "Description Two",
                color = "#ffd46c".toNoteColor()
            )
        )
        assert(notesDao.getAllNotesAsFlow().first().size == 2)
    }
}

