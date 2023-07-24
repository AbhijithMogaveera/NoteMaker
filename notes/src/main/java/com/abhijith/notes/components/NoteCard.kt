package com.abhijith.notes.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object NoteCardSemantics {
    const val noteIsSelected: String = "This note is selected"
}


@Composable
fun AnimatingSizeBox(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    targetValue: Dp,
    animationSpec: AnimationSpec<Dp>,
    content: @Composable BoxScope.() -> Unit,
) {
    val size by animateDpAsState(
        targetValue = if (!isVisible) 0.dp else targetValue,
        animationSpec = animationSpec
    )
    if (size != 0.dp) {
        Box(modifier = modifier
            .size(targetValue)
            .testTag(tag = "content")) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(size),
                content = content
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun NoteCard(
    containerColor: Color,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .combinedClickable(onClick = onClick, onLongClick = onLongClick),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = if (isSelected) BorderStroke(1.dp, Color.Black) else null,
    ) {
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 4
                )
            }

            Box(modifier = Modifier.fillMaxSize(0.5f)){

            }

            AnimatingSizeBox(
                isVisible = isSelected,
                animationSpec = tween(300),
                targetValue = 40.dp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black.copy(alpha = 0.7f), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = NoteCardSemantics.noteIsSelected,
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }

    }

}