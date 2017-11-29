package pl.understandable.understandable_app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import org.json.JSONArray;
import org.json.JSONObject;

import pl.understandable.understandable_app.user.AchievementChecker;
import pl.understandable.understandable_app.user.RequestExecutor;
import pl.understandable.understandable_app.user.SyncManager;
import pl.understandable.understandable_app.user.TimeLearntManager;
import pl.understandable.understandable_app.user.UserManager;
import pl.understandable.understandable_app.utils.AdUtil;
import pl.understandable.understandable_app.utils.font.Font;
import pl.understandable.understandable_app.utils.font.FontsOverride;

import java.io.File;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular-PL.ttf");
        prepareCustomWordsSetsDirectory();
        Font.loadBuiltInTypefaces(getApplicationContext());

        AdUtil.init(getApplicationContext());

        UserManager.init();
        SyncManager.init((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        RequestExecutor.init();
        TimeLearntManager.init();
        AchievementChecker.init();

        try {
            JSONObject user = new JSONObject();
            user.put("name", "Anonymous");
            user.put("exp", 1552342);
            JSONObject stats = new JSONObject();
            stats.put("timeLearnt", 450000);
            stats.put("testsDownloaded", 12);
            stats.put("allTestsSolved", 156);
            JSONArray wordsTestsSolved = new JSONArray();
            wordsTestsSolved.put(23).put(13).put(50).put(70);
            JSONArray irregularVerbsTestsSolved = new JSONArray();
            irregularVerbsTestsSolved.put(0).put(0);
            JSONArray phrasesTestsSolved = new JSONArray();
            phrasesTestsSolved.put(0).put(0).put(0);
            stats.put("wordsTestsSolved", wordsTestsSolved);
            stats.put("irregularVerbsTestsSolved", irregularVerbsTestsSolved);
            stats.put("phrasesTestsSolved", phrasesTestsSolved);
            user.put("stats", stats);
            JSONObject achievements = new JSONObject();
            achievements.put("id1", true);
            achievements.put("id2", true);
            achievements.put("id3", false);
            achievements.put("id4", true);
            achievements.put("id5", false);
            user.put("achievements", achievements);
            JSONArray downloadedTests = new JSONArray();
            downloadedTests.put("AABBCC").put("XXYYZZ").put("ZASDBC");
            user.put("downloadedTests", downloadedTests);
            System.out.println("=========================================================");
            System.out.println(user.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
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
