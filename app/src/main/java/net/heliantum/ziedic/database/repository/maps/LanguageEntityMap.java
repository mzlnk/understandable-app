package net.heliantum.ziedic.database.repository.maps;

import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.database.entity.LanguageEntity;

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

    public List<LanguageEntity> getSpecifiedEntities(List<LanguageCategory> categories, List<LanguageType> types) {
        List<LanguageEntity> result = new ArrayList<>();
        for(LanguageEntity entity : languageEntities) {
            for(LanguageCategory category : categories) {
                for(LanguageType type : types) {
                    if(entity.getCategory().equals(category) && entity.getType().equals(type)) {
                        result.add(entity);
                    }
                }
            }
        }
        return result;
    }

}
