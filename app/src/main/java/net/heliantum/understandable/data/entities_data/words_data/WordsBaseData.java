package net.heliantum.understandable.data.entities_data.words_data;

import net.heliantum.understandable.data.entities_data.BaseData;
import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.database.entity.WordEntity;
import net.heliantum.understandable.database.repository.WordEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin on 2017-05-06.
 */

public abstract class WordsBaseData implements BaseData<WordEntity, WordsDataParams> {

    protected static final Random r = new Random();

    protected WordsDataParams params;
    protected List<WordEntity> words = new ArrayList<>();

    public WordsBaseData(WordsDataParams params) {
        this.params = params;
        generateWords();
    }

    @Override
    public List<WordEntity> getEntities() {
        return words;
    }

    @Override
    public WordsDataParams getParams() {
        return params;
    }

    @Override
    public void generateWords() {
        words = WordEntityRepository.getSpecifiedEntities(params.categories, params.types);
        resize();
    }

    private void resize() {
        List<WordEntity> all = new ArrayList<>(words);
        words.clear();
        for(int i = 0; i < params.size; i++) {
            WordEntity item = all.get(r.nextInt(all.size()));
            words.add(item);
            all.remove(item);
        }
    }

}
