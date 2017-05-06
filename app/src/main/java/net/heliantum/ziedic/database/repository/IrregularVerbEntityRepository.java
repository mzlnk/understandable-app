package net.heliantum.ziedic.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import net.heliantum.ziedic.database.entity.IrregularVerbEntity;
import net.heliantum.ziedic.database.repository.maps.IrregularVerbEntityMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class IrregularVerbEntityRepository {

    private static IrregularVerbEntityMap irregularVerbEntityMap = new IrregularVerbEntityMap();

    private static final String FILE_PATH = "data/irregular_verb_entities.json";
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
            irregularVerbEntityMap = new Gson().fromJson(br, IrregularVerbEntityMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<IrregularVerbEntity> getAllEntities() {
        return irregularVerbEntityMap.getAllEntities();
    }

}
