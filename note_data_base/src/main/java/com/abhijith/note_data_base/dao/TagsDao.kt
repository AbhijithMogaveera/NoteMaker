package com.abhijith.note_data_base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteToTags
import com.abhijith.note_data_base.models.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagsDao {

    @Insert
    suspend fun insertNoteTagMap(tags: NoteToTags)

    @Insert
    suspend fun insertTag(tag: Tag)

    @Query("select * from Tags")
    fun getAllTag(): Flow<List<Tag>>

    @Transaction
    @Query(
        "SELECT * FROM Note " +
                "INNER JOIN NoteToTags ON Note.note_id = NoteToTags.note_id " +
                "WHERE NoteToTags.tag_id = :tagId"
    )
    suspend fun getNoteWithTag(tagId: Long): List<Note>


    @Query("SELECT * FROM NoteToTags")
    suspend fun getAllNoteToTag(): List<NoteToTags>


}