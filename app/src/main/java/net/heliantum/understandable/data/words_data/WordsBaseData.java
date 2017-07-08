package net.heliantum.understandable.data.words_data;

import net.heliantum.understandable.data.params.WordsDataParams;
import net.heliantum.understandable.database.entity.LanguageEntity;
import net.heliantum.understandable.database.repository.LanguageEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin on 2017-05-06.
 */

public abstract class WordsBaseData {

    protected static final Random r = new Random();

    protected WordsDataParams params;
    protected List<LanguageEntity> words = new ArrayList<>();

    public WordsBaseData(WordsDataParams params) {
        this.params = params;
        generateWords();
    }

    public List<LanguageEntity> getWords() {
        return words;
    }

    public WordsDataParams getParams() {
        return params;
    }

    public void generateWords() {
        words = LanguageEntityRepository.getSpecifiedEntities(params.categories, params.types);
        resize();
    }

    private void resize() {
        List<LanguageEntity> all = new ArrayList<>(words);
        words.clear();
        for(int i = 0; i < params.size; i++) {
            LanguageEntity item = all.get(r.nextInt(all.size()));
            words.add(item);
            all.remove(item);
        }
    }

}
