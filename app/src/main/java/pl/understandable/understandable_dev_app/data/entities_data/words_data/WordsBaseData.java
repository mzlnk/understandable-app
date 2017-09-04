package pl.understandable.understandable_dev_app.data.entities_data.words_data;

import pl.understandable.understandable_dev_app.data.entities_data.Datable;
import pl.understandable.understandable_dev_app.data.params.WordsDataParams;
import pl.understandable.understandable_dev_app.database.entity.WordEntity;
import pl.understandable.understandable_dev_app.database.repository.WordEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public abstract class WordsBaseData implements Datable<WordEntity, WordsDataParams> {

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
        switch(params.method) {
            case ALL:
                words = WordEntityRepository.getSpecifiedEntities(params.categories);
                break;
            case SUBCATEGORIES:
                words = WordEntityRepository.getSpecifiedEntitiesBySubcategory(params.categories, params.subcategories);
                break;
            case TYPES:
                words = WordEntityRepository.getSpecifiedEntitiesByType(params.categories, params.types);
                break;
        }
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
