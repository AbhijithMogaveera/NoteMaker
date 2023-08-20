package com.abhijith.notes.components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.abhijith.note_data_base.models.NoteColor

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PopUpContent(
    onColorSelected: (NoteColor) -> Unit = {},
    selectedColor: NoteColor,
    colorList: List<NoteColor> = NoteColor.getColor()
) {
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
            shape = RoundedCornerShape(
                topEnd = 10.dp,
                topStart = 30.dp,
                bottomEnd = 30.dp,
                bottomStart = 30.dp
            )
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Select Your color", color = Color.White, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp), fontSize = 22.sp
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                content = {
                    colorList.onEach { noteColor ->
                        val color = Color(noteColor.color.toColorInt())
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
                                        onColorSelected(noteColor)
                                    },
                                    border = if (selectedColor == noteColor) {
                                        BorderStroke(2.dp, Color.Black)
                                    } else null
                                ) {
                                }
                                if (selectedColor.color == noteColor.color) {
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