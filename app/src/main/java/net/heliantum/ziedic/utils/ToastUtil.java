package net.heliantum.ziedic.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Marcin on 2017-01-29.
 */

public class ToastUtil {

    public static void showToastMessage(Context context, String message, int duration) {
        final Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }

}
