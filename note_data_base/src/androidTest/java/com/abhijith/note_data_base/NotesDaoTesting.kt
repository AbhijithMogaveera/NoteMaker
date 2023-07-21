package com.abhijith.note_data_base

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
    lateinit var notesDatabase: NotesDatabase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test(){
        assert(true)
    }
    @After
    fun tearDown() {

    }
}

