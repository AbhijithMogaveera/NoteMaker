package com.abhijith.notes.screens.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.abhijith.note_data_base.exceptions.EmptyResource
import com.abhijith.notes.R
import com.abhijith.notes.components.dialogs.PopUpContent
import com.abhijith.notes.databinding.NoteCreationBinding
import com.abhijith.notes.viewmodels.NoteCreationScreenViewModel
import com.abhijith.theme.NoteTakingTheme
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteUpsertFragment : BindingFragment<NoteCreationBinding>() {

    private var showColorSelectionPopup by mutableStateOf(false)

    private val viewModel by viewModels<NoteCreationScreenViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): NoteCreationBinding {
        return NoteCreationBinding.inflate(inflater, container, false).apply {
            this.composeContent.setContent {
                val selectedColor by animateColorAsState(
                    targetValue = Color(viewModel.selectedNoteColor.color.toColorInt()),
                    label = ""
                )
                NoteTakingTheme {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                navigationIcon = {
                                    NavigationIcon()
                                },
                                title = {
                                    Title()
                                },
                                actions = {
                                    Actions()
                                },
                                colors = TopAppBarDefaults
                                    .smallTopAppBarColors(containerColor = Color.Transparent)
                            )
                        },
                        containerColor = selectedColor,
                        floatingActionButton = {
                            FloatingActionButton()
                        }
                    ) {
                        ScreenContent(it)
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationIcon() {
        IconButton(onClick = {
            findNavController().navigate(R.id.destNoteListing)
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }

    @Composable
    private fun Title() {
        Text(text = "Create Your Note")
    }

    @Composable
    private fun RowScope.Actions() {
        IconButton(onClick = {
            showColorSelectionPopup = !showColorSelectionPopup
        }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null
            )
        }
    }

    @Composable
    private fun FloatingActionButton() {
        FloatingActionButton(onClick = {
            lifecycleScope.launch {
                viewModel.saveNote()
                    .catch {
                        when (it) {
                            is EmptyResource -> {
                                Toast.makeText(
                                    requireContext(),
                                    "Note discarded",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigateUp()
                            }
                        }
                    }.collectLatest {
                        Toast.makeText(
                            requireContext(),
                            "Note saved successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()
                    }

            }
        }) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun ScreenContent(it: PaddingValues) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (showColorSelectionPopup) {
                ColorSelectionPopup()
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = viewModel::onTitleChanged,
                    Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Title...")
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = viewModel::onDescriptionChanged,
                    Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 200.dp),
                    placeholder = {
                        Text(text = "Description...")
                    }
                )
            }
        }
    }

    @Composable
    private fun ColorSelectionPopup() {
        Popup(
            alignment = Alignment.TopEnd,
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            onDismissRequest = {
                showColorSelectionPopup = !showColorSelectionPopup
            },
        ) {
            PopUpContent(
                onColorSelected = viewModel::setNoteColor,
                selectedColor = viewModel.selectedNoteColor
            )
        }
    }

}

