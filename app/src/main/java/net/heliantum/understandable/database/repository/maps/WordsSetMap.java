package net.heliantum.understandable.database.repository.maps;

import net.heliantum.understandable.database.entity.WordsSetEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Marcin on 2017-07-27.
 */

public class WordsSetMap {

    private Map<String, WordsSetEntity> wordsSetEntities = new HashMap<>();

    public WordsSetEntity getEntity(String id) {
        return wordsSetEntities.get(id);
    }

    public List<WordsSetEntity> getAllEntities() {
        return new ArrayList<>(wordsSetEntities.values());
    }

    public void addEntity(WordsSetEntity entity) {
        wordsSetEntities.put(entity.getId(), entity);
    }

    public void removeEntity(String id) {
        wordsSetEntities.remove(id);
    }

}
