package net.heliantum.understandable;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import net.heliantum.understandable.corrupted.BaseDBHandler;
import net.heliantum.understandable.data.enums.ThemeType;
import net.heliantum.understandable.database.repository.IrregularVerbEntityRepository;
import net.heliantum.understandable.database.repository.LanguageEntityRepository;
import net.heliantum.understandable.utils.font.Font;
import net.heliantum.understandable.utils.font.FontsOverride;

/**
 * Created by Lotos_ on 2016-11-11.
 */

public class App extends Application {

    private static final String DEBUG_TAG = "ZiedicData";

    private static BaseDBHandler dr;

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
        Font.loadBuiltInTypefaces(getApplicationContext());
    }

    //todo: fix it
    public static void onStop() {
        Log.d(DEBUG_TAG, "Closing database connection");
        //dr.close();
        Log.d(DEBUG_TAG, "Database connection closed");
    }

    private void loadData() {
        Log.d(DEBUG_TAG, "Overriding default font");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular-PL.ttf");

        Log.d(DEBUG_TAG, "Loading Entities");
        LanguageEntityRepository.init(getApplicationContext());
        IrregularVerbEntityRepository.init(getApplicationContext());
    }

}
