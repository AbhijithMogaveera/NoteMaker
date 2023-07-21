package com.abhijith.note_data_base

import com.abhijith.note_data_base.dao.NotesDao
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
    public var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun noteInsertionTest() = runBlocking {
        notesDao.insert(
            Note(
                title = "",
                description = ""
            )
        )
        assert(notesDao.getAll().first().size == 1)
    }

    @Test
    fun noteDeletionTest() = runBlocking {
        notesDao.insert(
            Note(
                title = "",
                description = ""
            )
        )
        val note = notesDao.getAll().first().first()
        notesDao.delete(note = note)
        assert(notesDao.getAll().first().isEmpty())
    }

    @Test
    fun noteUpdateTest() = runBlocking {
        notesDao.insert(
            Note(
                title = "old title",
                description = "old description"
            )
        )
        val newTitle = "New title"
        val note = notesDao.getAll().first().first().copy(title = newTitle)
        assert(notesDao.update(note = note) !=-1)
        assert(notesDao.getAll().first().size == 1)
        assert(notesDao.getAll().first().first().title == newTitle){
            notesDao.getAll().first()
        }


    }
    @Test
    fun getAllNoteTest() = runBlocking{
        notesDao.insert(
            Note(
                title = "Note 1",
                description = "Description One"
            )
        )
        notesDao.insert(
            Note(
                title = "Note 2",
                description = "Description Two"
            )
        )
        assert(notesDao.getAll().first().size == 2)
    }

    @After
    fun tearDown() {

    }
}

