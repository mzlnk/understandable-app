package pl.understandable.understandable_app.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.util.TypedValue;

/**
 * Created by Marcin on 2017-07-06.
 */

public class ColorUtil {

    private Context context;

    public ColorUtil(Context context) {
        this.context = context;
    }

    public int getColor(int attrResId) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attrResId, typedValue, true);
        @ColorInt int color = typedValue.data;
        return color;
    }
}
