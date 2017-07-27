package net.heliantum.understandable.database.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.heliantum.understandable.database.entity.WordsSetEntity;
import net.heliantum.understandable.database.repository.maps.WordsSetMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin on 2017-07-27.
 */

public class WordsSetsRepository {

    private static WordsSetMap wordsSetMap = new WordsSetMap();

    private static final String FILE_PATH = "words_sets/info/words_sets_info.json";
    private static File dataFile;

    public static void init(Context context) {
        dataFile = new File(context.getFilesDir(), FILE_PATH);
        loadData();
    }

    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile)))) {
            wordsSetMap = new Gson().fromJson(br, WordsSetMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveData() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(wordsSetMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WordsSetEntity getEntity(String id) {
        return wordsSetMap.getEntity(id);
    }

    public static List<WordsSetEntity> getAllEntities() {
        return wordsSetMap.getAllEntities();
    }

    public static void addEntity(WordsSetEntity entity) {
        wordsSetMap.addEntity(entity);
        saveData();
    }

    public static void removeEntity(String id) {
        wordsSetMap.removeEntity(id);
        saveData();
    }

    public static void setName(String id, String name) {
        WordsSetEntity entity = getEntity(id);
        if(entity != null) {
            entity.setName(name);
            saveData();
        }
    }

    public static void setDescription(String id, String description) {
        WordsSetEntity entity = getEntity(id);
        if(entity != null) {
            entity.setDescription(description);
            saveData();
        }
    }
}
