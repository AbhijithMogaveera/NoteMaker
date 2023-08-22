package com.abhijith.note_data_base.dao

import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.models.NoteToTags
import com.abhijith.note_data_base.models.Tag
import com.abhijith.note_data_base.models.toNoteColor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class TagsDaoTesting {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var tagsDao: TagsDao

    @Inject
    lateinit var notesDao: NotesDao

    @Before
    fun startUp() {
        hiltRule.inject()
    }

    @Test
    fun insertTag(): Unit = runBlocking {
        tagsDao.insertTag(
            Tag(
                name = "Food"
            )
        )
        assert(tagsDao.getAllTag().first().size == 1)
    }

    @Test
    fun mapANoteToTag(): Unit = runBlocking {
        tagsDao.insertTag(Tag(name = "Food"))
        notesDao.insertNote(
            Note(
                title = "Note title",
                description = "Note description",
                color = NoteColor.getColor().random()
            )
        )
        val insertedTag = tagsDao.getAllTag().first().first()
        val insertedNote = notesDao.getAllNotesAsFlow().first().first()
        notesDao.getAllNotesAsFlow().first()
        tagsDao.insertNoteTagMap(
            NoteToTags(
                note_id = insertedNote.note_id,
                tag_id = insertedTag.tag_id
            )
        )
        val noteWithTag = tagsDao.getNoteWithTag(insertedTag.tag_id)
        assert(noteWithTag.isNotEmpty())
    }
}