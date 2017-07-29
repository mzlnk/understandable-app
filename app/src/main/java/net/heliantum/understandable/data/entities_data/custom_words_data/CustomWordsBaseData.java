package net.heliantum.understandable.data.entities_data.custom_words_data;

import net.heliantum.understandable.data.entities_data.BaseData;
import net.heliantum.understandable.data.params.CustomWordsDataParams;
import net.heliantum.understandable.database.entity.CustomWordEntity;
import net.heliantum.understandable.database.repository.CustomWordEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin on 2017-07-29.
 */

public abstract class CustomWordsBaseData implements BaseData<CustomWordEntity, CustomWordsDataParams> {

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
