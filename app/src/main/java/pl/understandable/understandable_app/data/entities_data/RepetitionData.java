package pl.understandable.understandable_app.data.entities_data;

import java.util.List;

import pl.understandable.understandable_app.database.entity.BaseWordEntity;

/**
 * Created by Marcin Zielonka on 2018-03-04.
 */

public interface RepetitionData<T extends BaseWordEntity> extends CurrentWordData<T> {

    public List<T> getWordsToRepeat();

}
