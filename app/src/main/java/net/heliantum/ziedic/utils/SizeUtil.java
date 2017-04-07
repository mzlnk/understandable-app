package net.heliantum.ziedic.utils;

import android.graphics.Point;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Marcin on 2017-04-07.
 */

public class SizeUtil {

    public static void setSize(View target, Point size) {
        int width = size.x;
        int height = size.y;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        target.setLayoutParams(layoutParams);
    }

}
