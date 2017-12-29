package pl.understandable.understandable_app.database.repository.temp;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.database.entity.CustomWordsSetEntity;

/**
 * Created by Marcin Zielonka on 2017-12-03.
 */

public class FollowedCustomWordsSetsRepository {

    private static List<CustomWordsSetEntity> wordsSetEntities = new ArrayList<>();

    public static List<CustomWordsSetEntity> getWordsSets() {
        return wordsSetEntities;
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

    public static void removeWordsSet(String id) {
        CustomWordsSetEntity wordsSet = getWordsSet(id);
        if(wordsSet != null) {
            wordsSetEntities.remove(wordsSet);
        }
    }

    public static void clearRepository() {
        wordsSetEntities.clear();
    }

}
