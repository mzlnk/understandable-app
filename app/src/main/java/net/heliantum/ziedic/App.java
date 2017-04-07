package net.heliantum.ziedic;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import net.heliantum.ziedic.database.DatabaseRepository;
import net.heliantum.ziedic.database.IrregularVerbEntityRepository;
import net.heliantum.ziedic.database.LanguageEntityRepository;
import net.heliantum.ziedic.utils.ScreenUtil;
import net.heliantum.ziedic.utils.font.FontsOverride;

/**
 * Created by Lotos_ on 2016-11-11.
 */

public class App extends Application {

    private static String DEBUG_TAG = "ZiedicData";

    private static DatabaseRepository dr;
    private static LanguageEntityRepository lr;
    private static IrregularVerbEntityRepository ir;

    public static LanguageEntityRepository getLanguageEntityRepositoryRepository() {
        return lr;
    }

    public static IrregularVerbEntityRepository getIrregularVerbEntityRepository() {
        return ir;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
        loadScreenUtil();
    }

    //todo: fix it
    public static void onStop() {

        Log.d(DEBUG_TAG, "Closing database connection");
        dr.close();
        Log.d(DEBUG_TAG, "Database connection closed");

    }

    private void loadData() {
        Log.d(DEBUG_TAG, "Overriding default font");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Montserrat-Regular-PL.ttf");

        Log.d(DEBUG_TAG, "Creating connection with internal database");
        dr = new DatabaseRepository(getApplicationContext()).open();

        Log.d(DEBUG_TAG, "Loading data: LanguageEntities");
        lr = new LanguageEntityRepository(getApplicationContext());
        lr.loadData();
        Log.d(DEBUG_TAG, "Loading data: IrregularVerbEntities");
        ir = new IrregularVerbEntityRepository(getApplicationContext());
        //todo: code here
        Log.d(DEBUG_TAG, "Data has been loaded");
    }

    private void loadScreenUtil() {
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ScreenUtil.init(size.x, size.y);
    }

}
