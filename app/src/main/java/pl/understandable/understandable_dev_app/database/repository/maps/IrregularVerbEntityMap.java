package pl.understandable.understandable_dev_app.database.repository.maps;

import pl.understandable.understandable_dev_app.database.entity.IrregularVerbEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class IrregularVerbEntityMap {

    private List<IrregularVerbEntity> irregularVerbEntities = new ArrayList<>();

    public List<IrregularVerbEntity> getAllEntities() {
        return new ArrayList<>(irregularVerbEntities);
    }

}
