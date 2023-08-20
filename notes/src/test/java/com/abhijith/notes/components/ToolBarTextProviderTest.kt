package com.abhijith.notes.components

import org.junit.Test


class ToolBarTextProviderTest {

    @Test
    fun testToolbarTextProvider(){
        val toolBarTextProvider = ToolBarTextProvider()
        assert(toolBarTextProvider.getText(NotesToolBarState.Default) == "Notes")
        assert(toolBarTextProvider.getText(NotesToolBarState.Selection(0)) == "No Items selected")
        assert(toolBarTextProvider.getText(NotesToolBarState.Selection(1)) == "1 Item selected")
        assert(toolBarTextProvider.getText(NotesToolBarState.Selection(2)) == "2 Items selected")
    }
}