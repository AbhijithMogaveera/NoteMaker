package com.abhijith.notes.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.fragment.findNavController
import com.abhijith.notes.R
import com.abhijith.notes.components.NoteCard
import com.abhijith.notes.databinding.NoteListingBinding
import com.abhijith.theme.NoteTakingTheme
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : BindingFragment<NoteListingBinding>() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): NoteListingBinding {
        return NoteListingBinding.inflate(inflater, container, false).apply {
            this.composeContent.setContent {
                NoteTakingTheme {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Notes")
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
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            content = {
                listOf(
                    "#fffbed",
                    "#5bc9ff",
                    "#ff6666",
                    "#ffd46c",
                    "#67ffc5",
                    "#fffbed",
                    "#5bc9ff",
                    "#ff6666",
                    "#ffd46c",
                    "#67ffc5",
                    "#fffbed",
                    "#5bc9ff",
                    "#ff6666",
                    "#ffd46c",
                    "#67ffc5",
                ).map { colorHex ->
                    Color(colorHex.toColorInt())
                }.onEach { color ->
                    item {

                        var isSelected by remember {
                            mutableStateOf(false)
                        }

                        NoteCard(
                            containerColor = color,
                            title = "Title",
                            description = "Description lorem ipsum hlleoasdfasdf asdfasf asfasdfo asofisdfasdfosjdfsdfsdjfsdfasdfasf asdofasdfjsafsdf",
                            isSelected = isSelected,
                            onLongClick = {
                                isSelected = true
                            },
                            onClick = {
                                isSelected = false
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
}


