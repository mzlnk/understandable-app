package net.heliantum.understandable.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import net.heliantum.understandable.database.entity.CustomWordEntity;
import net.heliantum.understandable.database.repository.maps.CustomWordEntityMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin on 2017-07-25.
 */

public class CustomWordEntityRepository {

    private static CustomWordEntityMap customWordEntityMap = new CustomWordEntityMap();

    private static InputStream dataFile;

    public static void init(Context context, String wordSetId) {
        clearData();
        try {
            File directory = new File(context.getFilesDir(), "/words_sets/");
            dataFile = new FileInputStream(new File(directory, wordSetId + ".json"));
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(dataFile))) {
            customWordEntityMap = new Gson().fromJson(br, CustomWordEntityMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clearData() {
        dataFile = null;
        customWordEntityMap = new CustomWordEntityMap();
    }

    public static List<CustomWordEntity> getAllEntities() {
        return customWordEntityMap.getAllEntities();
    }

}
