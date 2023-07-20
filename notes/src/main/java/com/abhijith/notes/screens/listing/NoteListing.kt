package com.abhijith.notes.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abhijith.notes.R
import com.abhijith.notes.databinding.NoteCreationBinding
import com.abhijith.notes.databinding.NoteListingBinding
import com.abhijith.theme.NoteTakingTheme
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListing: BindingFragment<NoteListingBinding>() {
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
                                Icon(imageVector = Icons.Default.Add, contentDescription =null )
                            }
                        }
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                        ){

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyBinding(binding: NoteListingBinding) {
        super.onDestroyBinding(binding)

    }
}