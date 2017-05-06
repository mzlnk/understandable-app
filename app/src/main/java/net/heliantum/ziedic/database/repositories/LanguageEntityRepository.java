package net.heliantum.ziedic.database.repositories;

import android.content.Context;

import net.heliantum.ziedic.data.enums.LanguageCategory;
import net.heliantum.ziedic.data.enums.LanguageType;
import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.database.handlers.LanguageEntityDBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class LanguageEntityRepository extends BaseEntityRepository {

    private List<LanguageEntity> languageEntities = new ArrayList<>();

    private static LanguageEntityRepository repository;

    public static void init(Context context) {
        repository = new LanguageEntityRepository();
        repository.loadEntitiesFromDatabase(context);
    }

    public static void reload(Context context) {
        repository.languageEntities.clear();
        init(context);
    }

    public static LanguageEntityRepository getRepository() {
        return repository;
    }

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

    private void loadEntitiesFromDatabase(Context context) {
        LanguageEntityDBHandler handler = new LanguageEntityDBHandler(context);
        languageEntities = handler.getData();
    }

}
