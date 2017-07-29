package net.heliantum.understandable.database.repository.maps;

import net.heliantum.understandable.data.enums.words.WordsLanguageCategory;
import net.heliantum.understandable.data.enums.words.WordsLanguageType;
import net.heliantum.understandable.database.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class WordEntityMap {

    private List<WordEntity> wordEntities = new ArrayList<>();

    public List<WordEntity> getAllEntities() {
        return new ArrayList<>(wordEntities);
    }

    public List<WordEntity> getSpecifiedEntities(List<WordsLanguageCategory> categories, List<WordsLanguageType> types) {
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
