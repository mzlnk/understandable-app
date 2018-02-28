package pl.understandable.understandable_app.database.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningWordsWay;
import pl.understandable.understandable_app.database.entity.CustomWordEntity;
import pl.understandable.understandable_app.database.repository.maps.CustomWordEntityMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-25.
 */

public class CustomWordEntityRepository {

    private static CustomWordEntityMap customWordEntityMap = new CustomWordEntityMap();

    private static File dataFile;

    public static void create(Context context, String wordSetId) {
        clearData();
        File directory = new File(context.getFilesDir(), "/words_sets/");
        dataFile = new File(directory, wordSetId + ".json");
        loadData();
    }

    private static void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            customWordEntityMap = new Gson().fromJson(br, CustomWordEntityMap.class);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveData() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(customWordEntityMap));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clearData() {
        dataFile = null;
        customWordEntityMap = new CustomWordEntityMap();
    }

    public void setWordLearnt(int id, boolean learnt) {
        for(CustomWordEntity entity : customWordEntityMap.getAllEntities()) {
            if(entity.getId() == id) {
                entity.setLearnt(learnt);
                break;
            }
        }
        saveData();
    }

    public static List<CustomWordEntity> getEntities(CustomWordsLearningWordsWay wordsWay) {
        return customWordEntityMap.getEntities(wordsWay);
    }

}
