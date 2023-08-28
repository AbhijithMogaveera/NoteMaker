package com.abhijith.notes.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.note_data_base.models.Note
import com.abhijith.note_data_base.usecase.DeleteNoteUseCase
import com.abhijith.note_data_base.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListingScreenViewModel
@Inject constructor(
    private val getNotesUseCase: GetNoteUseCase,
    private val deleteNotesUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _selectedItemIds = MutableStateFlow<List<Long>>(emptyList())
    val selectedItemIds = _selectedItemIds
    var isNotedIsGettingDeleted by mutableStateOf(false)

    fun deleteSelectedNoted(){
        val ids = _selectedItemIds.value
        viewModelScope.launch {
            isNotedIsGettingDeleted  =true
            delay(500)
            ids.forEach {
                deleteNotesUseCase.deleteNote(it).first()
            }
            isNotedIsGettingDeleted = false
        }
    }

    fun onToggleNoteSelection(id: Long) {
        _selectedItemIds.update {
            if(!it.contains(id))
                it+id
            else
                it-id

        }
    }

    fun isInSelectionMode() = _selectedItemIds.value.isNotEmpty()

    fun onNoteSelectionCleared() {
        _selectedItemIds.update{ emptyList() }
    }

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        viewModelScope.launch {
            getNotesUseCase
                .getAllNotesUpdatesInFlow()
                .collectLatest(_notes::emit)
        }
    }
}