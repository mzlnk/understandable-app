package net.heliantum.understandable.database.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.heliantum.understandable.database.entity.CustomWordsSetEntity;
import net.heliantum.understandable.database.repository.maps.CustomWordsSetMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin on 2017-07-27.
 */

public class CustomWordsSetsRepository {

    private static CustomWordsSetMap customWordsSetMap = new CustomWordsSetMap();

    private static final String FILE_PATH = "words_sets/info/words_sets_info.json";
    private static File dataFile;

    public static void init(Context context) {
        dataFile = new File(context.getFilesDir(), FILE_PATH);
        loadData();
    }

    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile)))) {
            customWordsSetMap = new Gson().fromJson(br, CustomWordsSetMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveData() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(customWordsSetMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustomWordsSetEntity getEntity(String id) {
        return customWordsSetMap.getEntity(id);
    }

    public static List<CustomWordsSetEntity> getAllEntities() {
        return customWordsSetMap.getAllEntities();
    }

    public static void addEntity(CustomWordsSetEntity entity) {
        customWordsSetMap.addEntity(entity);
        saveData();
    }

    public static void removeEntity(String id) {
        customWordsSetMap.removeEntity(id);
        saveData();
    }

    public static void setName(String id, String name) {
        CustomWordsSetEntity entity = getEntity(id);
        if(entity != null) {
            entity.setName(name);
            saveData();
        }
    }

    public static void setDescription(String id, String description) {
        CustomWordsSetEntity entity = getEntity(id);
        if(entity != null) {
            entity.setDescription(description);
            saveData();
        }
    }
}
