package net.heliantum.understandable.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lotos_ on 2016-11-15.
 */


public class Debug {

    public static boolean toShow = false;

    public static void sendMessage(Context context, String message, int length) {
        if(toShow) {
            Toast.makeText(context, message, length).show();
        }
    }

}
