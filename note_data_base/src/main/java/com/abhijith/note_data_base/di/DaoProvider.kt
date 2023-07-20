package com.abhijith.note_data_base.di

import android.app.Application
import androidx.room.Room
import com.abhijith.note_data_base.NotesDatabase
import com.abhijith.note_data_base.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoProvider{

    @Provides
    @Singleton
    fun getNotesDao(
        notesDatabase: NotesDatabase
    ): NotesDao {
        return notesDatabase.getUserDao()
    }

}

