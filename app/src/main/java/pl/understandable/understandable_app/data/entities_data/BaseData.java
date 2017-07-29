package pl.understandable.understandable_app.data.entities_data;

import pl.understandable.understandable_app.data.params.BaseDataParams;
import pl.understandable.understandable_app.database.entity.BaseEntity;

import java.util.List;

/**
 * Created by Marcin on 2017-07-29.
 */

public abstract interface BaseData<E extends BaseEntity, P extends BaseDataParams> {

    public List<E> getEntities();
    public P getParams();
    public void generateWords();

}
