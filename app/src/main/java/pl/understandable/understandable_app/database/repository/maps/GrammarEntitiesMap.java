package pl.understandable.understandable_app.database.repository.maps;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.database.entity.GrammarFillGapEntity;
import pl.understandable.understandable_app.database.entity.GrammarQuizEntity;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarEntitiesMap {

    private List<GrammarQuizEntity> grammarQuizEntities = new ArrayList<>();
    private List<GrammarFillGapEntity> grammarFillGapEntities = new ArrayList<>();

    public List<GrammarQuizEntity> getGrammarQuizEntities() {
        return grammarQuizEntities;
    }

    public List<GrammarFillGapEntity> getGrammarFillGapEntities() {
        return grammarFillGapEntities;
    }

}
