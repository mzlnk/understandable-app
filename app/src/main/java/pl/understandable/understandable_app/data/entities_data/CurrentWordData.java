package pl.understandable.understandable_app.data.entities_data;

import pl.understandable.understandable_app.database.entity.BaseEntity;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public interface CurrentWordData<T extends BaseEntity> {

    public T getCurrentWord();

}
