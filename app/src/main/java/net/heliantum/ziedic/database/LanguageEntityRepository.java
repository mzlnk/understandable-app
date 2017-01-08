package net.heliantum.ziedic.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.database.entity.LanguageEntites;
import net.heliantum.ziedic.database.entity.LanguageEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marcin on 2017-01-08.
 */

public class LanguageEntityRepository extends DatabaseRepository {

    public LanguageEntityRepository(Context context) {
        super(context);
    }

    public void loadData() {
        Cursor c;
        //table 'words':
        c = db.rawQuery("SELECT * FROM 'words'", null);

        if(c.moveToFirst()) {
            do {
                LanguageEntites.addEntity(new LanguageEntity(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        LanguageCategory.getEnum(c.getString(3)),
                        LanguageType.getEnum(c.getString(4))));
            }while(c.moveToNext());
        }
    }

    public void updateData() {

        //todo: remove in beta version (it'll have been replaced by MYSQL on VPS)

        Log.d(DEBUG_TAG, "Starting updating local database...");

        Scanner sc;
        List<String> statements = new ArrayList<>();
        InputStream is = null;

        try {
            is = context.getAssets().open("sql_statements/sql_temp_statements.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc = new Scanner(is);

        while(sc.hasNextLine()) {
            statements.add(sc.nextLine());
        }

        for(String statement : statements) {
            Log.d(DEBUG_TAG, "Executing statement: " + statement);
            db.execSQL(statement);
        }

        Log.d(DEBUG_TAG, "Executing statements has finished");
    }

}
