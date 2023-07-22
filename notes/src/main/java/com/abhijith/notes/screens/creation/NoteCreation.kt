package com.abhijith.notes.screens.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.graphics.toColorInt
import androidx.navigation.fragment.findNavController
import com.abhijith.notes.R
import com.abhijith.notes.databinding.NoteCreationBinding
import com.abhijith.theme.NoteTakingTheme
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteCreation : BindingFragment<NoteCreationBinding>() {
    var selectedColor: Color? by mutableStateOf(null)
    var showColorSelectionPopup by mutableStateOf(false)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): NoteCreationBinding {
        return NoteCreationBinding.inflate(inflater, container, false).apply {
            this.composeContent.setContent {
                val selectedColor by animateColorAsState(
                    targetValue = selectedColor ?: Color.Transparent
                )
                NoteTakingTheme {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                navigationIcon = {
                                    IconButton(onClick = {
                                        findNavController().navigate(R.id.destNoteListing)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = null
                                        )
                                    }
                                },
                                title = {
                                    Text(text = "Create Your Note")
                                },
                                actions = {
                                    IconButton(onClick = {
                                        showColorSelectionPopup = !showColorSelectionPopup
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = Color.Transparent
                                )
                            )
                        },
                        containerColor = selectedColor,
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                findNavController().navigateUp()
                            }) {
                                Icon(imageVector = Icons.Default.Done, contentDescription = null)
                            }
                        }
                    ) {
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
                                var title by remember { mutableStateOf("") }
                                OutlinedTextField(
                                    value = title,
                                    onValueChange = {
                                        title = it
                                    },
                                    Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(text = "Title...")
                                    }
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                var desc by remember { mutableStateOf("") }
                                OutlinedTextField(
                                    value = desc,
                                    onValueChange = {
                                        desc = it
                                    },
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
                }
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
            PopUpContent()
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun PopUpContent() {
        Box(
            modifier = Modifier.padding(5.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                elevation = CardDefaults.cardElevation(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.8f)
                ),
                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 30.dp, bottomEnd = 30.dp, bottomStart = 30.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Select Your color", color = Color.White, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp), fontSize = 22.sp)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    content = {
                        listOf(
                            "#fffbed",
                            "#5bc9ff",
                            "#ff6666",
                            "#ffd46c",
                            "#67ffc5"
                        ).map { colorHex ->
                            Color(colorHex.toColorInt())
                        }.onEach { color ->
                            item {
                                Box() {
                                    Card(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .size(60.dp)
                                            .clip(
                                                FloatingActionButtonDefaults.smallShape
                                            )
                                            .background(color = color),
                                        colors = CardDefaults.cardColors(
                                            containerColor = color
                                        ),
                                        onClick = {
                                            selectedColor = color
                                        },
                                        border = if (selectedColor == color) {
                                            BorderStroke(2.dp, Color.Black)
                                        } else null
                                    ) {
                                    }
                                    if (selectedColor == color) {
                                        IconButton(
                                            onClick = {
                                            }, modifier = Modifier.align(
                                                Alignment.Center
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Done,
                                                contentDescription = null,

                                                )
                                        }
                                    }

                                }
                            }
                        }

                    },
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(15.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    override fun onDestroyBinding(binding: NoteCreationBinding) {
        super.onDestroyBinding(binding)

    }

}