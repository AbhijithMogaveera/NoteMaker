package com.abhijith.notes.anim

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBox(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    targetValue: Dp,
    animationSpec: AnimationSpec<Dp>,
    content: @Composable BoxScope.() -> Unit,
) {
    val size by animateDpAsState(
        targetValue = if (!isVisible) 0.dp else targetValue,
        animationSpec = animationSpec, label = ""
    )
    if (size != 0.dp) {
        Box(
            modifier = modifier
                .size(targetValue)
                .testTag(tag = "content")
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(size),
                content = content
            )
        }
    }

}