package com.abhijith.notes.util

import android.graphics.Color

object ColorUtils {
    fun getContrastColor(backgroundColor: Int): Int {
        val brightness =
            calculateBrightness(backgroundColor)
        return if (brightness < 128) Color.WHITE else Color.BLACK
    }

    private fun calculateBrightness(color: Int): Double {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return 0.299 * red + 0.587 * green + 0.114 * blue
    }
}