package com.abhijith.note_data_base.di

import android.app.Application
import androidx.room.Room
import com.abhijith.note_data_base.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NoteDatabaseProvider{

    @Provides @Singleton
    fun getDb(
        application: Application
    ): NotesDatabase {
        return Room.databaseBuilder(
            application,
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }

}

