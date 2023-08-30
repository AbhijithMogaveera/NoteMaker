package com.abhijith.notes.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed class NotesToolBarState {
    object Default : NotesToolBarState()
    class Selection(
        val count: Int
    ) : NotesToolBarState()
}

internal class ToolBarTextProvider {
    fun getText(
        state: NotesToolBarState
    ): String {
        return when (state) {
            is NotesToolBarState.Default -> "Notes"
            is NotesToolBarState.Selection -> if (state.count <= 0) "No Items selected" else "${state.count} ${if (state.count == 1) "Item" else "Items"} selected"
        }
    }
}

interface ToolBarCallBack {
    fun onNavigationIconClick(state: NotesToolBarState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesToolBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.smallTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    state: NotesToolBarState,
    toolBarCallBack: ToolBarCallBack
) {
    TopAppBar(
        title = {
            Text(text = ToolBarTextProvider().getText(state))
        },
        modifier = modifier,
        navigationIcon = when (state) {
            is NotesToolBarState.Default -> {
                {

                }
            }

            is NotesToolBarState.Selection -> {
                {
                    IconButton(onClick = {
                        toolBarCallBack.onNavigationIconClick(state)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Dismiss Selection"
                        )
                    }
                }
            }
        },
        actions,
        windowInsets,
        colors,
        scrollBehavior,
    )
}