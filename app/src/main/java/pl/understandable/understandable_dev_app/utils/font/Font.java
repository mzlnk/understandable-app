package pl.understandable.understandable_dev_app.utils.font;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Created by Marcin Zielonka on 2017-04-07.
 */

public class Font {

    public static Typeface TYPEFACE_MONTSERRAT;

    private Typeface typeface;
    private float size;
    private int color = Color.BLACK;

    public static void loadBuiltInTypefaces(Context context) {
        TYPEFACE_MONTSERRAT = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular-PL.ttf");
    }

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
