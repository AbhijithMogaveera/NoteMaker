package com.abhijith.note_data_base.dao

import android.app.Application
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abhijith.note_data_base.NotesDatabase
import com.abhijith.note_data_base.di.NoteDatabaseProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NoteDatabaseProvider::class]
)
object TestDbProvider {
    @Provides
    @Singleton
    fun getDb(
        application: Application
    ): NotesDatabase {
        return Room.inMemoryDatabaseBuilder(
            application,
            NotesDatabase::class.java
        ).build()
    }
}

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NotesDaoTesting {
    @Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var notesDatabase: NotesDatabase

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }
}