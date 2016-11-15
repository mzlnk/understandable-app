package net.heliantum.ziedic.database;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lotos_ on 2016-11-11.
 */

public class DataHandler extends Application {

    private static String DEBUG_TAG = "ZiedicData";

    private static DatabaseRepository dr;

    public static DatabaseRepository getDatabaseRepository() {
        return dr;
    }

    @Override
    public void onCreate() {

        Log.d(DEBUG_TAG, "Preparing to load data...");
        super.onCreate();

        dr = new DatabaseRepository(getApplicationContext());
        dr.open();
        dr.loadData();
        Log.d(DEBUG_TAG, "Data has been loaded");
    }

    public static void onStop() {

        Log.d(DEBUG_TAG, "Closing database connection");
        dr.close();
        Log.d(DEBUG_TAG, "Database connection closed");

    }

}
