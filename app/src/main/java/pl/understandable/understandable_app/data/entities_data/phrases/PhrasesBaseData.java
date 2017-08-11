package pl.understandable.understandable_app.data.entities_data.phrases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.understandable.understandable_app.data.entities_data.BaseData;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.database.repository.PhraseEntityRepository;

/**
 * Created by Marcin on 2017-08-11.
 */

public class PhrasesBaseData implements BaseData<PhraseEntity, PhrasesDataParams> {

    protected static final Random r = new Random();

    protected PhrasesDataParams params;
    protected List<PhraseEntity> words = new ArrayList<>();

    public PhrasesBaseData(PhrasesDataParams params) {
        this.params = params;
        generateWords();
    }

    @Override
    public List<PhraseEntity> getEntities() {
        return words;
    }

    @Override
    public PhrasesDataParams getParams() {
        return params;
    }

    @Override
    public void generateWords() {
        words = PhraseEntityRepository.getAllEntities();
    }

}
