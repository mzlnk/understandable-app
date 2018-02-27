package pl.understandable.understandable_app.database.repository.maps;

import pl.understandable.understandable_app.database.entity.CustomWordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-07-25.
 */

public class CustomWordEntityMap {

    private List<CustomWordEntity> customWordEntities = new ArrayList<>();

    public List<CustomWordEntity> getAllEntities() {
        return customWordEntities;
    }



}
