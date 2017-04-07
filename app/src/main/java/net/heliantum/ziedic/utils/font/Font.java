package net.heliantum.ziedic.utils.font;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Created by Marcin on 2017-04-07.
 */

public class Font {

    private Typeface typeface;
    private float size;
    private int color = Color.BLACK;

    public Font(Typeface typeface, float size, int color) {
        this.typeface = typeface;
        this.size = size;
        this.color = color;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public float getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

}
