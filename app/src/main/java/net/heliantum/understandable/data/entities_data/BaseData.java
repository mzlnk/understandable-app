package net.heliantum.understandable.data.entities_data;

import net.heliantum.understandable.data.params.BaseDataParams;
import net.heliantum.understandable.database.entity.BaseEntity;

import java.util.List;

/**
 * Created by Marcin on 2017-07-29.
 */

public abstract interface BaseData<E extends BaseEntity, P extends BaseDataParams> {

    public List<E> getEntities();
    public P getParams();
    public void generateWords();

}
