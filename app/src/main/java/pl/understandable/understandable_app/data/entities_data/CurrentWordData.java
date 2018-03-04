package pl.understandable.understandable_app.data.entities_data;

import pl.understandable.understandable_app.database.entity.BaseWordEntity;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public interface CurrentWordData<T extends BaseWordEntity> {

    public T getCurrentWord();

}
