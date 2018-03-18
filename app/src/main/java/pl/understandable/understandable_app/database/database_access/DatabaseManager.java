package pl.understandable.understandable_app.database.database_access;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Marcin on 2018-03-01.
 */

public class DatabaseManager extends SQLiteAssetHelper {

    public static SQLiteDatabase database;

    public static void init(Context context) {
        DatabaseManager manager = new DatabaseManager(context);
        database = manager.getWritableDatabase();
    }

    public static void closeDatabase() {
        database.close();
    }

    private static final String DATABASE_NAME = "entities.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
