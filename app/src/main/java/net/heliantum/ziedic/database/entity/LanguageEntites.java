package net.heliantum.ziedic.database.entity;

import net.heliantum.ziedic.data.LanguageCategory;
import net.heliantum.ziedic.data.LanguageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class LanguageEntites {

    private static List<LanguageEntity> languageEntities = new ArrayList<>();

    public static void reset() {
        languageEntities = new ArrayList<>();
    }

    public static void addEntity(LanguageEntity entity) {
        languageEntities.add(entity);
    }

    public static LanguageEntity getEntity(int id) {
        for(LanguageEntity entity : languageEntities) {
            if(entity.getId() == id) return entity;
        }
        return null;
    }

    public static List<LanguageEntity> getSpecifiedEntities(List<LanguageCategory> categories, List<LanguageType> types) {

        List<LanguageEntity> result = new ArrayList<>();

        for(LanguageEntity entity : languageEntities) {
            for(LanguageCategory category : categories) {
                for(LanguageType type : types) {
                    if(entity.getCategory().equals(category) && entity.getType().equals(type)) result.add(entity);
                }
            }
        }

        return result;
    }

    public static int size() {
        return languageEntities.size();
    }

    private static boolean exists(List<LanguageEntity> list, LanguageEntity entity) {

        for(LanguageEntity e : list) {
            if(e.getId() == entity.getId()) return true;
        }
        return false;
    }


}
