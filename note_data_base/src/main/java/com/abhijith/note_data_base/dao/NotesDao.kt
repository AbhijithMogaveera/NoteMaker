package com.abhijith.note_data_base.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.abhijith.note_data_base.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    suspend fun insert(note: Note):Unit

    @Update
    suspend fun update(note: Note):Unit

    @Delete
    suspend fun delete(note: Note):Unit

    @Query("select * from Note")
    fun getAll():Flow<List<Note>>

}