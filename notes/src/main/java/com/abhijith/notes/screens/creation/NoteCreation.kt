package com.abhijith.notes.screens.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.fragment.findNavController
import com.abhijith.notes.R
import com.abhijith.notes.databinding.NoteCreationBinding
import com.abhijith.theme.NoteTakingTheme
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteCreation : BindingFragment<NoteCreationBinding>() {

    var showColorSelectionPopup by mutableStateOf(false)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): NoteCreationBinding {
        return NoteCreationBinding.inflate(inflater, container, false).apply {
            this.composeContent.setContent {
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
                                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                                    }
                                }
                            )
                        },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            if(showColorSelectionPopup){
                                Popup(
                                    alignment = Alignment.Center,
                                    properties = PopupProperties(
                                        focusable = true,
                                        dismissOnBackPress = true,
                                        dismissOnClickOutside = true,
                                    ),
                                    onDismissRequest = {
                                        showColorSelectionPopup =! showColorSelectionPopup
                                    }
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth(0.8f)
                                            .aspectRatio(1f),
                                        elevation = CardDefaults.cardElevation(

                                        )
                                    ) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyBinding(binding: NoteCreationBinding) {
        super.onDestroyBinding(binding)

    }

}