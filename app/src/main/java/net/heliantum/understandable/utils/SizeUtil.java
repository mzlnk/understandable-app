package net.heliantum.understandable.utils;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;


/**
 * Created by Marcin on 2017-04-07.
 */

public class SizeUtil {

    private static final String TABLE_ROW = TableRow.class.getName();
    private static final String TABLE_LAYOUT = TableLayout.class.getName();

    private Class<? extends ViewGroup> type;
    private int width, height;

    public SizeUtil(Class<? extends ViewGroup> type) {
        this.type = type;
    }


    public void setSize(View target, Point size) {
        width = (int)((double)size.x * ScreenUtil.getScreen().widthRatio);
        height = (int)((double)size.y * ScreenUtil.getScreen().heightRatio);

        String name = type.getName();
        if(name.equals(TABLE_ROW)) {
            useTableRowLayout(target);
        } else if(name.equals(TABLE_LAYOUT)) {
            useTableLayout(target);
        } else {
            useBaseLayout(target);
        }
    }

    private void useTableRowLayout(View target) {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width, height);
        target.setLayoutParams(layoutParams);
    }

    private void useTableLayout(View target) {
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(width, height);
        target.setLayoutParams(layoutParams);
    }

    private void useBaseLayout(View target) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
        target.setLayoutParams(layoutParams);
        try {
            throw new Exception("Used BaseLayout!");
        } catch (Exception e) {
        }
    }

}
