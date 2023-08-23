package com.abhijith.notes.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.usecase.NoteGetUserCase
import com.abhijith.note_data_base.usecase.NoteInsertUseCase
import com.abhijith.note_data_base.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteUpsertViewModel
@Inject constructor(
    val useCaseNoteInsertion: NoteInsertUseCase,
    val useCaseGetUserCase: NoteGetUserCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel(

) {
    companion object{
        enum class Mode{
            CRETE, EDIT
        }
    }
    val noteId = savedStateHandle.get<Long>("note_id")
    val mode get() = if(noteId!=null) Mode.EDIT else Mode.CRETE
    var title by mutableStateOf(""); private set
    fun onTitleChanged(
        title: String,
    ) {
        this.title = title
    }

    var description by mutableStateOf(""); private set
    var selectedNoteColor by mutableStateOf(NoteColor.getColor().random()); private set

    fun setNoteColor(color: NoteColor) {
        selectedNoteColor = color
    }

    fun onDescriptionChanged(
        description: String,
    ) {
        this.description = description
    }

    suspend fun saveNote(): Flow<Any> {
        if (noteId != null) {
            return updateNoteUseCase.updateNote(
                note = useCaseGetUserCase.getNoteById(noteId).first().copy(
                    title = title,
                    description = description,
                    color = selectedNoteColor
                ),
                deleteIfBlank = true
            )
        }
        return useCaseNoteInsertion.insertNote(
            title, description, selectedNoteColor
        )
    }

    init {
        viewModelScope.launch {
            if (noteId != null) {
                useCaseGetUserCase.getNoteById(noteId).catch {
                    Log.e("Error", "while fetching note id $noteId")
                }.first().also { fetchedNote ->
                    onTitleChanged(fetchedNote.title)
                    onDescriptionChanged(fetchedNote.description)
                    setNoteColor(fetchedNote.color)
                }
            }
        }
    }

}