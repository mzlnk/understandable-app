package net.heliantum.ziedic.database.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class LanguageEntites {

    private List<LanguageEntity> entities = new ArrayList<>();

    public void addEntity(LanguageEntity entity) {
        entities.add(entity);
    }

    public LanguageEntity getEntity(int id) {
        for(LanguageEntity entity : entities) {
            if(entity.getId() == id) return entity;
        }
        return null;
    }

    public List<LanguageEntity> getSpecifiedEntities(List<LanguageCategory> categories, List<LanguageType> types) {

        List<LanguageEntity> result = new ArrayList<>();

        for(LanguageEntity entity : entities) {
            for(LanguageCategory category : categories) {
                for(LanguageType type : types) {
                    if(entity.getCategory().equals(category) && entity.getType().equals(type)) result.add(entity);
                }
            }
        }

        return result;
    }

    public int size() {
        return entities.size();
    }

    private static boolean exists(List<LanguageEntity> list, LanguageEntity entity) {

        for(LanguageEntity e : list) {
            if(e.getId() == entity.getId()) return true;
        }
        return false;
    }


}
