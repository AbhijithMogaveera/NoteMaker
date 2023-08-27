package com.abhijith.notes.util;

import android.graphics.Color;

public class ColorUtils {

    public static int getContrastColor(int backgroundColor) {
        double brightness = ColorUtils.calculateBrightness(backgroundColor);
        int textColor = (brightness < 128) ? Color.WHITE : Color.BLACK;
        return textColor;
    }

    public static double calculateBrightness(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return (0.299 * red + 0.587 * green + 0.114 * blue);
    }
}