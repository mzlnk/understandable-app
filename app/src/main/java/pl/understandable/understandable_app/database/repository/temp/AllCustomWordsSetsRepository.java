package pl.understandable.understandable_app.database.repository.temp;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;

/**
 * Created by Marcin on 2017-09-09.
 */

public class AllCustomWordsSetsRepository {

    private static List<CustomWordsSetEntity> wordsSetEntities = new ArrayList<>();

    public static List<CustomWordsSetEntity> getWordsSets() {
        return wordsSetEntities;
    }

    public static List<CustomWordsSetEntity> getWordSets(String search) {
        if(search.isEmpty() || search.equalsIgnoreCase("")) {
            return wordsSetEntities;
        }
        List<CustomWordsSetEntity> result = new ArrayList<>();
        for(CustomWordsSetEntity entity : wordsSetEntities) {
            if(entity.getName().toLowerCase().contains(search.toLowerCase())) result.add(entity);
        }
        return result;
    }

    public static CustomWordsSetEntity getWordsSet(String id) {
        for(CustomWordsSetEntity entity : wordsSetEntities) {
            if(entity.getId().equalsIgnoreCase(id)) {
                return entity;
            }
        }
        return null;
    }

    public static void addWordsSet(CustomWordsSetEntity wordsSet) {
        wordsSetEntities.add(wordsSet);
    }

    public static void clearRepository() {
        wordsSetEntities.clear();
    }

}
