package pl.understandable.understandable_app.corrupted;

import pl.understandable.understandable_app.data.enums.words.WordsCategory;
import pl.understandable.understandable_app.data.enums.words.WordsLearningWordsWay;
import pl.understandable.understandable_app.data.enums.words.WordsSubcategory;
import pl.understandable.understandable_app.data.enums.words.WordsType;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class WordEntityMap {

    private List<WordEntity> wordEntities = new ArrayList<>();

    public List<WordEntity> getAllEntities() {
        return new ArrayList<>(wordEntities);
    }

    public List<WordEntity> getSpecifiedEntitiesByCategory(WordsDataParams params) {
        List<WordEntity> result = new ArrayList<>();
        boolean onlyNotLearned = params.wordsWay.equals(WordsLearningWordsWay.NOT_LEARNED);
        for(WordEntity entity : wordEntities) {
            for(WordsCategory category : params.categories) {
                if (entity.getCategory().equals(category)) {
                    if(onlyNotLearned && !entity.isLearnt()) {
                        result.add(entity);
                    } else {
                        result.add(entity);
                    }
                }
            }
        }
        return result;
    }

    public List<WordEntity> getSpecifiedEntitiesBySubcategory(WordsDataParams params) {
        List<WordEntity> result = new ArrayList<>();
        boolean onlyNotLearned = params.wordsWay.equals(WordsLearningWordsWay.NOT_LEARNED);
        for(WordEntity entity : wordEntities) {
            for(WordsCategory category : params.categories) {
                for(WordsSubcategory subcategory : params.subcategories) {
                    if(entity.getCategory().equals(category) && entity.getSubcategory().equals(subcategory)) {
                        if(onlyNotLearned && !entity.isLearnt()) {
                            result.add(entity);
                        } else {
                            result.add(entity);
                        }
                    }
                }
            }
        }
        return result;
    }

    public List<WordEntity> getSpecifiedEntitiesByType(WordsDataParams params) {
        List<WordEntity> result = new ArrayList<>();
        boolean onlyNotLearned = params.wordsWay.equals(WordsLearningWordsWay.NOT_LEARNED);
        for(WordEntity entity : wordEntities) {
            for(WordsCategory category : params.categories) {
                for(WordsType type : params.types) {
                    if(entity.getCategory().equals(category) && entity.getType().equals(type)) {
                        if(onlyNotLearned && !entity.isLearnt()) {
                            result.add(entity);
                        } else {
                            result.add(entity);
                        }
                    }
                }
            }
        }
        return result;
    }

}
