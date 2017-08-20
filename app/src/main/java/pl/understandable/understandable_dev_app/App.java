package pl.understandable.understandable_dev_app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import pl.understandable.understandable_dev_app.utils.AdUtil;
import pl.understandable.understandable_dev_app.utils.font.Font;
import pl.understandable.understandable_dev_app.utils.font.FontsOverride;

import java.io.File;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular-PL.ttf");
        prepareCustomWordsSetsDirectory();
        Font.loadBuiltInTypefaces(getApplicationContext());
        AdUtil.init(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private void prepareCustomWordsSetsDirectory() {
        File customWordsSetsDirectory = new File(getFilesDir(), "/words_sets/");
        File customWordsSetsInfoDirectory = new File(getFilesDir(), "/words_sets/info/");
        if(!customWordsSetsDirectory.exists()) {
            customWordsSetsDirectory.mkdir();
        }
        if(!customWordsSetsInfoDirectory.exists()) {
            customWordsSetsInfoDirectory.mkdir();
        }
    }

}
