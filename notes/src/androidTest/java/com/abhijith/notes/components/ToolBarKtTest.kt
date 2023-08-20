package com.abhijith.notes.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ToolBarKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun validateNormalStateToolBar() {
        composeTestRule.setContent {
            NotesToolBar(
                state = NotesToolBarState.Default,
                toolBarCallBack = object :ToolBarCallBack{
                    override fun onNavigationIconClick(state: NotesToolBarState) {

                    }
                }
            )
        }
        composeTestRule.onNodeWithText("Notes").assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun validateSelectedStateToolBar() {
        composeTestRule.setContent {
            NotesToolBar(
                state = NotesToolBarState.Selection(10),
                toolBarCallBack = object :ToolBarCallBack{
                    override fun onNavigationIconClick(state: NotesToolBarState) {
                    }
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("Dismiss Selection").apply {
            assertIsDisplayed()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkClicks(){
        var isIcDismissClicked = false
        composeTestRule.setContent {
            NotesToolBar(
                state = NotesToolBarState.Selection(10),
                toolBarCallBack = object :ToolBarCallBack{
                    override fun onNavigationIconClick(state: NotesToolBarState) {
                        isIcDismissClicked = true
                    }
                }
            )
        }
        composeTestRule.onNodeWithContentDescription("Dismiss Selection").apply {
            performClick()
            assert(isIcDismissClicked)
        }
    }

}