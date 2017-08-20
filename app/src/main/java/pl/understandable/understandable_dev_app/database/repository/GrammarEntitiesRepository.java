package pl.understandable.understandable_dev_app.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_dev_app.data.enums.grammar.GrammarLearningMode;
import pl.understandable.understandable_dev_app.database.entity.GrammarBaseEntity;
import pl.understandable.understandable_dev_app.database.repository.maps.GrammarEntitiesMap;

/**
 * Created by Marcin Zielonka on 2017-08-12.
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

    public static List<?extends GrammarBaseEntity> getGrammarEntities(GrammarLearningMode mode) {
        switch(mode) {
            case QUIZ:
                return grammarEntitiesMap.getGrammarQuizEntities();
            case FILL_GAPS:
                return grammarEntitiesMap.getGrammarFillGapEntities();
        }
        return new ArrayList<>();
    }

}
