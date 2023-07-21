package com.abhijith.note_data_base.di

import android.app.Application
import androidx.room.Room
import com.abhijith.note_data_base.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [NoteDatabaseProvider::class]
//)
//object TestNoteDatabaseProvider {
//    @Provides
//    fun getDb(
//        application: Application
//    ): NotesDatabase {
//        return Room.inMemoryDatabaseBuilder(
//            application,
//            NotesDatabase::class.java
//        ).build()
//    }
//}
