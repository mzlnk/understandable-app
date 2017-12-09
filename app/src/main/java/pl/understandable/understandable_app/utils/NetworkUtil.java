package pl.understandable.understandable_app.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marcin Zielonka on 2017-08-02.
 */

public class NetworkUtil {

    public static boolean isNetworkAvailable(ConnectivityManager manager) {
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if(activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }

        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500);
            urlc.connect();

            return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
        } catch (IOException e) {
            Log.e("SYNC-CONNECTION", "Error checking internet connection", e);
            return false;
        }
    }

}
