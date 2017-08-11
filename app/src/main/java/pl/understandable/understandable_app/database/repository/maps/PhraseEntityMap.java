package pl.understandable.understandable_app.database.repository.maps;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.database.entity.PhraseEntity;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhraseEntityMap {

    private List<PhraseEntity> phraseEntities = new ArrayList<>();

    public List<PhraseEntity> getAllEntities() {
        return phraseEntities;
    }
}
