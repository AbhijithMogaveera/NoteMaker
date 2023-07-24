package com.abhijith.notes.components

import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class NoteCardKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    private val title = "Title"
    private val description = "Description lorem ipsum hlleoasdfasdf asdfasf " +
            "asfasdfo asofisdfasdfosjdfs" +
            "dfsdjfsdfasdfasf asdofasdfjsafsdf"

    @Test
    fun clicksTest(): Unit {
        runBlocking {
            var isClicked: Boolean = false
            var onLongPress: Boolean = false
            composeTestRule.setContent {
                Box(modifier = Modifier) {
                    NoteCard(
                        containerColor = Color.Green,
                        title = title,
                        description = description,
                        isSelected = false,
                        onLongClick = {
                            onLongPress = true
                        },
                        onClick = {
                            isClicked = true
                        }
                    )
                }
            }
            composeTestRule
                .onNodeWithText(title)
                .apply {
                    performClick()
                    assert(isClicked)
                    performTouchInput {
                        longClick()
                    }
                    assert(onLongPress)
                }
        }
    }

    @Test
    fun checkUnSelectedStateUI(): Unit = runBlocking {
        composeTestRule.setContent {
            Box(modifier = Modifier) {
                NoteCard(
                    containerColor = Color.Green,
                    title = title,
                    description = description,
                    isSelected = false,
                )
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(NoteCardSemantics.noteIsSelected)
            .assertDoesNotExist()
    }

    @Test
    fun checkUnSelectedStateUIAnimationTest(): Unit = runBlocking {
        composeTestRule.setContent {
            Box(modifier = Modifier) {
                NoteCard(
                    containerColor = Color.Green,
                    title = title,
                    description = description,
                    isSelected = false,
                )
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(NoteCardSemantics.noteIsSelected)
            .assertDoesNotExist()
        composeTestRule.mainClock
    }

    @Test
    fun checkSelectedStateUIAnimationTest(): Unit = runBlocking {
        composeTestRule.setContent {
            Box(modifier = Modifier) {
                NoteCard(
                    containerColor = Color.Green,
                    title = title,
                    description = description,
                    isSelected = true,
                )
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(NoteCardSemantics.noteIsSelected)
            .assertIsDisplayed()
        composeTestRule.mainClock
    }

    @Test
    fun animationTestOfSizedBox(): Unit = runBlocking {
        var isVisible by mutableStateOf(false)
        composeTestRule.setContent {
            AnimatingSizeBox(
                isVisible = isVisible,
                targetValue = 100.dp,
                animationSpec = tween(durationMillis = 2000),
                modifier = Modifier
            ) {
                Box(modifier = Modifier
                    .background(color = Color.Green)
                    .fillMaxSize()) {

                }
            }
        }
        isVisible = true
        composeTestRule.mainClock.advanceTimeBy(2000)
        composeTestRule.onNodeWithTag("content")
            .assertIsDisplayed()
            .assertHeightIsEqualTo(100.dp)
            .assertWidthIsEqualTo(100.dp)
        isVisible = false
        composeTestRule.mainClock.advanceTimeBy(2000)
        composeTestRule.onNodeWithTag("content")
            .assertDoesNotExist()

    }

}