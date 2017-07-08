package net.heliantum.understandable.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import net.heliantum.understandable.data.enums.words.WordsLanguageCategory;
import net.heliantum.understandable.data.enums.words.WordsLanguageType;
import net.heliantum.understandable.database.entity.LanguageEntity;
import net.heliantum.understandable.database.repository.maps.LanguageEntityMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class LanguageEntityRepository {

    private static LanguageEntityMap languageEntityMap = new LanguageEntityMap();

    private static final String FILE_PATH = "data/language_entities.json";
    private static InputStream dataFile;

    public static void init(Context context) {
        try {
            dataFile = context.getAssets().open(FILE_PATH);
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(dataFile))) {
            languageEntityMap = new Gson().fromJson(br, LanguageEntityMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<LanguageEntity> getAllEntities() {
        return languageEntityMap.getAllEntities();
    }

    public static List<LanguageEntity> getSpecifiedEntities(List<WordsLanguageCategory> categories, List<WordsLanguageType> types) {
        return languageEntityMap.getSpecifiedEntities(categories, types);
    }

}
