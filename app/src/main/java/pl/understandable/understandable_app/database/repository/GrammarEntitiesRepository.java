package pl.understandable.understandable_app.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import pl.understandable.understandable_app.database.entity.GrammarFillGapEntity;
import pl.understandable.understandable_app.database.entity.GrammarQuizEntity;
import pl.understandable.understandable_app.database.entity.GrammarSentenceEntity;
import pl.understandable.understandable_app.database.repository.maps.GrammarEntitiesMap;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarEntitiesRepository {

    private static GrammarEntitiesMap grammarEntitiesMap = new GrammarEntitiesMap();

    private static InputStream dataFile;

    public static void create(Context context, String id) {
        try {
            String filePath = "data/grammar/" + id + ".json";
            dataFile = context.getAssets().open(filePath);
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(dataFile));
            grammarEntitiesMap = new Gson().fromJson(br, GrammarEntitiesMap.class);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<GrammarQuizEntity> getGrammarQuizEntities() {
        return grammarEntitiesMap.getGrammarQuizEntities();
    }

    public static List<GrammarFillGapEntity> getGrammarFillGapEntities() {
        return grammarEntitiesMap.getGrammarFillGapEntities();
    }

    public static List<GrammarSentenceEntity> getGrammarSentenceEntities() {
        return grammarEntitiesMap.getGrammarSentenceEntities();
    }


}
