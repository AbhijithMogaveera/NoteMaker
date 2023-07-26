package com.abhijith.note_data_base.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abhijith.note_data_base.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    suspend fun insertNote(note: Note):Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note): Int

    @Delete
    suspend fun delete(note: Note):Int

    @Query("select * from Note")
    fun getAllNotesAsFlow(): Flow<List<Note>>

    @Query("select * from Note")
    suspend fun getAllNotes(): List<Note>

    @Query("select * from Note where note_id = (:id)")
    suspend fun getNoteById(id: Long): Note?

    @Query("delete from Note where note_id = (:id)")
    suspend fun delete(id: Long):Int

}