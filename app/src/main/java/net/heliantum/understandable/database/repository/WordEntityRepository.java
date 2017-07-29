package net.heliantum.understandable.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import net.heliantum.understandable.data.enums.words.WordsLanguageCategory;
import net.heliantum.understandable.data.enums.words.WordsLanguageType;
import net.heliantum.understandable.database.entity.WordEntity;
import net.heliantum.understandable.database.repository.maps.WordEntityMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class WordEntityRepository {

    private static WordEntityMap wordEntityMap = new WordEntityMap();

    private static final String FILE_PATH = "data/word_entities.json";
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
            wordEntityMap = new Gson().fromJson(br, WordEntityMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<WordEntity> getAllEntities() {
        return wordEntityMap.getAllEntities();
    }

    public static List<WordEntity> getSpecifiedEntities(List<WordsLanguageCategory> categories, List<WordsLanguageType> types) {
        return wordEntityMap.getSpecifiedEntities(categories, types);
    }

}
