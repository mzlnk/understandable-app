package pl.understandable.understandable_app.database.database_access;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Marcin on 2018-03-01.
 */

public class WordEntityDatabaseManager extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "word_entities.db";
    private static final int DATABASE_VERSION = 1;

    public WordEntityDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
