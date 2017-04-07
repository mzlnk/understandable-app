package net.heliantum.ziedic.utils.font;

import android.util.TypedValue;
import android.widget.TextView;

import net.heliantum.ziedic.utils.ScreenUtil;

/**
 * Created by Marcin on 2017-04-07.
 */

public class FontUtil {

    private static final float SCREEN_RATE = (float) ScreenUtil.getScreen().width / 360F;

    public static void setFont(TextView target, Font font) {
        target.setTypeface(font.getTypeface());
        target.setTextColor(font.getColor());
        target.setTextSize(TypedValue.COMPLEX_UNIT_PX, font.getSize() * SCREEN_RATE);
    }

}
