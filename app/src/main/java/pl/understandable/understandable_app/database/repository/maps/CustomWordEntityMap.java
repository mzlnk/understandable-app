package pl.understandable.understandable_app.database.repository.maps;

import pl.understandable.understandable_app.data.enums.custom_words.CustomWordsLearningWordsWay;
import pl.understandable.understandable_app.database.entity.CustomWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-25.
 */

public class CustomWordEntityMap {

    private List<CustomWordEntity> customWordEntities = new ArrayList<>();

    public List<CustomWordEntity> getAllEntities() {
        return customWordEntities;
    }

    public List<CustomWordEntity> getEntities(CustomWordsLearningWordsWay wordsWay) {
        if(wordsWay.equals(CustomWordsLearningWordsWay.ALL_WORDS)) {
            return customWordEntities;
        } else {
            List<CustomWordEntity> entities = new ArrayList<>();
            for(CustomWordEntity e : customWordEntities) {
                if(!e.isLearnt()) {
                    entities.add(e);
                }
            }
            return entities;
        }
    }



}
