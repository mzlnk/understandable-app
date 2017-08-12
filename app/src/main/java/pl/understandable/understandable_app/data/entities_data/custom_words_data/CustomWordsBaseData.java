package pl.understandable.understandable_app.data.entities_data.custom_words_data;

import pl.understandable.understandable_app.data.entities_data.Datable;
import pl.understandable.understandable_app.data.params.CustomWordsDataParams;
import pl.understandable.understandable_app.database.entity.CustomWordEntity;
import pl.understandable.understandable_app.database.repository.CustomWordEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin on 2017-07-29.
 */

public abstract class CustomWordsBaseData implements Datable<CustomWordEntity, CustomWordsDataParams> {

    protected static final Random r = new Random();

    protected CustomWordsDataParams params;
    protected List<CustomWordEntity> words = new ArrayList<>();

    public CustomWordsBaseData(CustomWordsDataParams params) {
        this.params = params;
        generateWords();
    }

    @Override
    public List<CustomWordEntity> getEntities() {
        return words;
    }

    @Override
    public CustomWordsDataParams getParams() {
        return params;
    }

    @Override
    public void generateWords() {
        words = CustomWordEntityRepository.getAllEntities();
    }

}
