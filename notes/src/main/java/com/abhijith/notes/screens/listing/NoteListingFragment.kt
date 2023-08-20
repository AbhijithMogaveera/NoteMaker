package com.abhijith.notes.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abhijith.notes.R
import com.abhijith.notes.components.NoteCard
import com.abhijith.notes.components.NotesToolBar
import com.abhijith.notes.components.NotesToolBarState
import com.abhijith.notes.components.ToolBarCallBack
import com.abhijith.notes.databinding.NoteListingBinding
import com.abhijith.notes.viewmodels.NoteListingScreenViewModel
import com.abhijith.theme.NoteTakingTheme
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : BindingFragment<NoteListingBinding>(), ToolBarCallBack {

    val noteListViewModel by viewModels<NoteListingScreenViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): NoteListingBinding {
        return NoteListingBinding.inflate(inflater, container, false).apply {
            this.composeContent.setContent {
                val selectedItemIds = noteListViewModel.selectedItemIds.collectAsState().value
                NoteTakingTheme {
                    Scaffold(
                        topBar = {
                            NotesToolBar(
                                state = if (selectedItemIds.isEmpty())
                                    NotesToolBarState.Default
                                else NotesToolBarState.Selection(selectedItemIds.size),
                                toolBarCallBack = this@NoteListingFragment,
                                actions = {
                                    if (selectedItemIds.isNotEmpty()) {
                                        IconButton(onClick = {
                                            noteListViewModel.deleteSelectedNoted()
                                            noteListViewModel.onNoteSelectionCleared()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Delete,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                    if (noteListViewModel.isNotedIsGettingDeleted) {
                                        CircularProgressIndicator()
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                findNavController().navigate(R.id.destNoteCreation)
                            }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            Content()
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun Content() {

        val notes by noteListViewModel.notes.collectAsState(initial = emptyList())
        val selectedItemIds = noteListViewModel.selectedItemIds.collectAsState().value

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            content = {
                notes.onEachIndexed { index, note ->
                    item {
                        NoteCard(
                            containerColor = Color(note.color.color.toColorInt()),
                            title = note.note_id.toString(),
                            description = note.description,
                            isSelected = selectedItemIds.contains(note.note_id),
                            onLongClick = {
                                noteListViewModel.onToggleNoteSelection(note.note_id)
                            },
                            onClick = {
                                if (!noteListViewModel.isInSelectionMode()) {
                                    Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    noteListViewModel.onToggleNoteSelection(note.note_id)
                                }
                            }
                        )
                    }

                }
                item {
                    Box(modifier = Modifier.height(100.dp))
                }
                item {
                    Box(modifier = Modifier.height(100.dp))
                }
            },
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        )

    }

    override fun onDestroyBinding(binding: NoteListingBinding) {
        super.onDestroyBinding(binding)
    }

    override fun onNavigationIconClick(state: NotesToolBarState) {
        noteListViewModel.onNoteSelectionCleared()
    }
}


