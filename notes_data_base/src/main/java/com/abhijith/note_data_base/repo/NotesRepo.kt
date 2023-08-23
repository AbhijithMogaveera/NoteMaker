package com.abhijith.note_data_base.repo

import arrow.core.Either
import com.abhijith.note_data_base.dao.NotesDao
import com.abhijith.note_data_base.exceptions.ResourceNotExists
import com.abhijith.note_data_base.models.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepoDefaultImpl
@Inject constructor(
    private val notesDao: NotesDao
) : NotesRepo {
    override suspend fun insertNote(note: Note): Either<Throwable, Long> = Either.catch {
        notesDao.insertNote(note)
    }

    override suspend fun getNoteById(id: Long): Either<Throwable, Note> = Either.catch {
        notesDao.getNoteById(id) ?: throw ResourceNotExists(id)
    }

    override suspend fun deleteNoteById(id: Long): Either<Throwable, Unit> = Either.catch {
        val deleteRecord = notesDao.delete(id)
        if (deleteRecord == 0)
            throw ResourceNotExists(id)
    }

    override suspend fun deleteNote(note: Note): Either<Throwable, Unit> = Either.catch {
        requireNotNull(note.note_id)
        val deleteRecord = notesDao.delete(note)
        if (deleteRecord == 0)
            throw ResourceNotExists(note.note_id)
    }

    override suspend fun updateNote(note: Note): Either<Throwable, Unit> = Either.catch {
        val count = notesDao.update(note)
        if (count == 0)
            throw ResourceNotExists(note.note_id)
    }

    override suspend fun getAllNotes(): Either<Throwable, List<Note>> = Either.catch {
        notesDao.getAllNotes()
    }

    override suspend fun getAllNotesUpdatesInFlow(): Flow<List<Note>> {
        return  notesDao.getAllNotesAsFlow()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NotesRepoProvider{

    @Provides @Singleton
    fun provideNoteRepo(
        notesDao: NotesDao
    ):NotesRepo{
        return NotesRepoDefaultImpl(notesDao)
    }

}

interface NotesRepo {

    /**
     * @return noted id of newly create note id
     * @see getNoteById
     * */
    suspend fun insertNote(note: Note): Either<Throwable, Long>

    /**
     * @return the note associated with [id]
     * @throws ResourceNotExists
     * */
    suspend fun getNoteById(id: Long): Either<Throwable, Note>

    /**
     * @param id id of the note to delete
     * @throws ResourceNotExists
     * */
    suspend fun deleteNoteById(
        id: Long
    ): Either<Throwable, Unit>

    /**
     * @param note id of the note to delete
     * @throws ResourceNotExists
     * */
    suspend fun deleteNote(
        note: Note
    ): Either<Throwable, Unit>


    /**
     * @param note note to delete from database
     * @throws ResourceNotExists
     * */
    suspend fun updateNote(note: Note): Either<Throwable, Unit>

    /**
     * @return all lists in data base
     * */
    suspend fun getAllNotes(): Either<Throwable, List<Note>>
    /**
     * @return all lists in data base
     * */
    suspend fun getAllNotesUpdatesInFlow(): Flow<List<Note>>

}