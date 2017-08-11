package pl.understandable.understandable_app.database.repository.maps;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesCategory;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageType;
import pl.understandable.understandable_app.data.enums.words.WordsLearningLevel;
import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.database.entity.WordEntity;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhraseEntityMap {

    private List<PhraseEntity> phraseEntities = new ArrayList<>();

    public List<PhraseEntity> getAllEntities() {
        return phraseEntities;
    }

    public List<PhraseEntity> getSpecifiedEntities(PhrasesCategory category) {
        List<PhraseEntity> result = new ArrayList<>();
        for(PhraseEntity entity : phraseEntities) {
            if(entity.getCategory().equals(category)) {
                result.add(entity);
            }
        }
        return result;
    }

}
