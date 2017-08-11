package pl.understandable.understandable_app.database.repository;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.database.repository.maps.PhraseEntityMap;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhraseEntityRepository {

    private static PhraseEntityMap phraseEntityMap = new PhraseEntityMap();

    private static final String FILE_PATH = "data/phrase_entities.json";
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
            phraseEntityMap = new Gson().fromJson(br, PhraseEntityMap.class);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<PhraseEntity> getAllEntities() {
        return phraseEntityMap.getAllEntities();
    }

}
