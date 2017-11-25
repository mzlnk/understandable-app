package pl.understandable.understandable_app.database.repository.maps;

import pl.understandable.understandable_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageSubcategory;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageType;
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

    public List<WordEntity> getSpecifiedEntities(List<WordsLanguageCategory> categories) {
        List<WordEntity> result = new ArrayList<>();
        for(WordEntity entity : wordEntities) {
            for(WordsLanguageCategory category : categories) {
                if (entity.getCategory().equals(category)) {
                    result.add(entity);
                }
            }
        }
        return result;
    }

    public List<WordEntity> getSpecifiedEntitiesBySubcategory(List<WordsLanguageCategory> categories, List<WordsLanguageSubcategory> subcategories) {
        List<WordEntity> result = new ArrayList<>();
        for(WordEntity entity : wordEntities) {
            for(WordsLanguageCategory category : categories) {
                for(WordsLanguageSubcategory subcategory : subcategories) {
                    if(entity.getCategory().equals(category) && entity.getSubcategory().equals(subcategory)) {
                        result.add(entity);
                    }
                }
            }
        }
        return result;
    }

    public List<WordEntity> getSpecifiedEntitiesByType(List<WordsLanguageCategory> categories, List<WordsLanguageType> types) {
        List<WordEntity> result = new ArrayList<>();
        for(WordEntity entity : wordEntities) {
            for(WordsLanguageCategory category : categories) {
                for(WordsLanguageType type : types) {
                    if(entity.getCategory().equals(category) && entity.getType().equals(type)) {
                        result.add(entity);
                    }
                }
            }
        }
        return result;
    }

}
