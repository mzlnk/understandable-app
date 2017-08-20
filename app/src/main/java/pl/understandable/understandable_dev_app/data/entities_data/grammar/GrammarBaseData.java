package pl.understandable.understandable_dev_app.data.entities_data.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.understandable.understandable_dev_app.data.entities_data.Datable;;
import pl.understandable.understandable_dev_app.data.params.GrammarDataParams;
import pl.understandable.understandable_dev_app.database.entity.GrammarBaseEntity;
import pl.understandable.understandable_dev_app.database.repository.GrammarEntitiesRepository;

/**
 * Created by Marcin Zielonka on 2017-08-12.
 */

public abstract class GrammarBaseData<T extends GrammarBaseEntity> implements Datable<T, GrammarDataParams> {

    protected static final Random r = new Random();

    protected GrammarDataParams params;
    protected List<T> words = new ArrayList<>();

    public GrammarBaseData(GrammarDataParams params) {
        this.params = params;
        generateWords();
    }

    @Override
    public List<T> getEntities() {
        return words;
    }

    @Override
    public GrammarDataParams getParams() {
        return params;
    }

    @Override
    public void generateWords() {
        words = (List<T>) GrammarEntitiesRepository.getGrammarEntities(params.mode);
    }

}
