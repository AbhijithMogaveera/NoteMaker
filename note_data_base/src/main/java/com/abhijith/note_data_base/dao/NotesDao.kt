package com.abhijith.note_data_base.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.abhijith.note_data_base.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    suspend fun insertNote(note: Note):Unit

    @Update(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun update(note: Note):Int

    @Delete
    suspend fun delete(note: Note):Unit

    @Query("select * from Note")
    fun getAllNotes():Flow<List<Note>>

}