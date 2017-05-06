package net.heliantum.ziedic.database.repository.maps;

import net.heliantum.ziedic.database.entity.IrregularVerbEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class IrregularVerbEntityMap {

    private List<IrregularVerbEntity> irregularVerbEntities = new ArrayList<>();

    public List<IrregularVerbEntity> getAllEntities() {
        return new ArrayList<>(irregularVerbEntities);
    }

}
