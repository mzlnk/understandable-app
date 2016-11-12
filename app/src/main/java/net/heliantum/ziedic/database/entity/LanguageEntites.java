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

    public List<LanguageEntity> getSpecifiedEntities(LanguageCategory[] categories, LanguageType[] types) {

        List<LanguageEntity> result = new ArrayList<>();

        if(categories.length == 0 && types.length == 0) {
            for(LanguageEntity entity : entities) {
                result.add(entity);
            }
        }

        for(LanguageCategory category : categories) {
            for(LanguageEntity entity : entities) {
                if(entity.getCategory().equals(category)) result.add(entity);
            }
        }

        for(LanguageType type : types) {
            for(LanguageEntity entity : entities) {
                if(entity.getType().equals(type) && !exists(result, entity)) result.add(entity);
            }
        }

        return result;
    }

    private static boolean exists(List<LanguageEntity> list, LanguageEntity entity) {

        for(LanguageEntity e : list) {
            if(e.getId() == entity.getId()) return true;
        }
        return false;
    }


}
