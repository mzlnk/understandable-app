package net.heliantum.understandable.utils.json_creator.pattern;

import net.heliantum.understandable.database.entity.CustomWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-07-25.
 */

public class CustomWordEntityPatternMap {

    private List<CustomWordEntity> customWordEntities = new ArrayList<>();

    public void addEntity(CustomWordEntity entity) {
        customWordEntities.add(entity);
    }

}
