package net.heliantum.understandable.database.repository.maps;

import net.heliantum.understandable.data.enums.words.WordsLanguageCategory;
import net.heliantum.understandable.data.enums.words.WordsLanguageType;
import net.heliantum.understandable.database.entity.LanguageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class LanguageEntityMap {

    private List<LanguageEntity> languageEntities = new ArrayList<>();

    public List<LanguageEntity> getAllEntities() {
        return new ArrayList<>(languageEntities);
    }

    public List<LanguageEntity> getSpecifiedEntities(List<WordsLanguageCategory> categories, List<WordsLanguageType> types) {
        List<LanguageEntity> result = new ArrayList<>();
        for(LanguageEntity entity : languageEntities) {
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
