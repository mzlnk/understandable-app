package net.heliantum.understandable.database.repository.maps;

import net.heliantum.understandable.database.entity.CustomWordsSetEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2017-07-27.
 */

public class CustomWordsSetMap {

    private Map<String, CustomWordsSetEntity> wordsSetEntities = new HashMap<>();

    public CustomWordsSetEntity getEntity(String id) {
        return wordsSetEntities.get(id);
    }

    public List<CustomWordsSetEntity> getAllEntities() {
        return new ArrayList<>(wordsSetEntities.values());
    }

    public void addEntity(CustomWordsSetEntity entity) {
        wordsSetEntities.put(entity.getId(), entity);
    }

    public void removeEntity(String id) {
        wordsSetEntities.remove(id);
    }

}
