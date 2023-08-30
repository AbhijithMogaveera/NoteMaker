package com.abhijith.notes.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.usecase.GetNoteUseCase
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
    private val useCaseNoteInsertion: NoteInsertUseCase,
    private val useCaseGetNote: GetNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel(

) {
    companion object {
        enum class Mode {
            CRETE, EDIT
        }
    }

    private val argNote = savedStateHandle.get<Note>("note")
    val mode get() = if (argNote != null) Mode.EDIT else Mode.CRETE
    var title by mutableStateOf(""); private set
    fun onTitleChanged(
        title: String,
    ) {
        this.title = title
    }

    var description by mutableStateOf(""); private set
    var selectedNoteColor by mutableStateOf(
        argNote?.color ?: NoteColor.getColor().random()
    ); private set

    fun setNoteColor(color: NoteColor) {
        selectedNoteColor = color
    }

    fun onDescriptionChanged(
        description: String,
    ) {
        this.description = description
    }

    suspend fun saveNote(): Flow<Any> {
        if (argNote != null) {
            return updateNoteUseCase.updateNote(
                note = useCaseGetNote.getNoteById(argNote.note_id).first().copy(
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
            if (argNote != null) {
                useCaseGetNote.getNoteById(argNote.note_id).catch {
                    Log.e("Error", "while fetching note id $argNote")
                }.first().also { fetchedNote ->
                    onTitleChanged(fetchedNote.title)
                    onDescriptionChanged(fetchedNote.description)
                    setNoteColor(fetchedNote.color)
                }
            }
        }
    }

}