package com.abhijith.notes.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.models.NoteColor
import com.abhijith.note_data_base.repo.NotesRepo
import com.abhijith.note_data_base.usecase.NoteInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteCreationScreenViewModel
@Inject constructor(
    val useCaseNoteInsertion: NoteInsertUseCase
) : ViewModel(

) {
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

    suspend fun saveNote() = useCaseNoteInsertion.insertNote(
        title, description, selectedNoteColor
    )

}