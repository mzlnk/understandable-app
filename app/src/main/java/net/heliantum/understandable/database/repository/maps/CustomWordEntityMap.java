package net.heliantum.understandable.database.repository.maps;

import net.heliantum.understandable.database.entity.CustomWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-07-25.
 */

public class CustomWordEntityMap {

    private List<CustomWordEntity> customWordEntities = new ArrayList<>();

    public List<CustomWordEntity> getAllEntities() {
        return customWordEntities;
    }

}
