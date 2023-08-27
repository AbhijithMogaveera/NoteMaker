package com.abhijith.note_taking

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt

object WindowUtil {
    fun initializeScreenshotSecurity(context: Context, window: Window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    fun clearLightNavigationBar(window: Window) {
        if (Build.VERSION.SDK_INT < 27) return
        clearSystemUiFlags(window, View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
    }

    fun setLightNavigationBar(window: Window) {
        if (Build.VERSION.SDK_INT < 27) return
        setSystemUiFlags(window, View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
    }

    fun setLightStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT < 23) return
        setSystemUiFlags(window, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    fun setStatusBarColor(window: Window, @ColorInt color: Int) {
        window.statusBarColor = color
    }

    fun getStatusBarColor(window: Window): Int {
        return window.statusBarColor
    }

    fun isStatusBarPresent(window: Window): Boolean {
        val rectangle = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        return rectangle.top > 0
    }

    private fun clearSystemUiFlags(window: Window, flags: Int) {
        val view = window.decorView
        var uiFlags = view.systemUiVisibility
        uiFlags = uiFlags and flags.inv()
        view.systemUiVisibility = uiFlags
    }

    private fun setSystemUiFlags(window: Window, flags: Int) {
        val view = window.decorView
        var uiFlags = view.systemUiVisibility
        uiFlags = uiFlags or flags
        view.systemUiVisibility = uiFlags
    }
}