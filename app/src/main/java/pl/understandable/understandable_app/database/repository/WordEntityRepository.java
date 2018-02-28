package pl.understandable.understandable_app.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import pl.understandable.understandable_app.data.enums.words.WordsCategory;
import pl.understandable.understandable_app.data.enums.words.WordsSubcategory;
import pl.understandable.understandable_app.data.enums.words.WordsType;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.entity.WordEntity;
import pl.understandable.understandable_app.database.repository.maps.WordEntityMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-06.
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
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(dataFile));
            wordEntityMap = new Gson().fromJson(br, WordEntityMap.class);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<WordEntity> getAllEntities() {
        return wordEntityMap.getAllEntities();
    }

    public static List<WordEntity> getSpecifiedEntitiesByCategory(WordsDataParams params) {
        return wordEntityMap.getSpecifiedEntitiesByCategory(params);
    }

    public static List<WordEntity> getSpecifiedEntitiesBySubcategory(WordsDataParams params) {
        return wordEntityMap.getSpecifiedEntitiesBySubcategory(params);
    }

    public static List<WordEntity> getSpecifiedEntitiesByType(WordsDataParams params) {
        return wordEntityMap.getSpecifiedEntitiesByType(params);
    }

}
