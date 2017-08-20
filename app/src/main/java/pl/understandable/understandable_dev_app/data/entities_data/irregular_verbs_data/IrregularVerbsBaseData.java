package pl.understandable.understandable_dev_app.data.entities_data.irregular_verbs_data;

import pl.understandable.understandable_dev_app.data.entities_data.Datable;
import pl.understandable.understandable_dev_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_dev_app.database.entity.IrregularVerbEntity;
import pl.understandable.understandable_dev_app.database.repository.IrregularVerbEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public abstract class IrregularVerbsBaseData implements Datable<IrregularVerbEntity, IrregularVerbsDataParams> {

    protected static final Random r = new Random();

    protected IrregularVerbsDataParams params;
    protected List<IrregularVerbEntity> words = new ArrayList<>();

    public IrregularVerbsBaseData(IrregularVerbsDataParams params) {
        this.params = params;
        generateWords();
    }

    @Override
    public List<IrregularVerbEntity> getEntities() {
        return words;
    }

    @Override
    public IrregularVerbsDataParams getParams() {
        return params;
    }

    @Override
    public void generateWords() {
        words = IrregularVerbEntityRepository.getAllEntities();
        resize();
    }

    private void resize() {
        List<IrregularVerbEntity> all = new ArrayList<>(words);
        words.clear();
        for(int i = 0; i < params.size; i++) {
            IrregularVerbEntity item = all.get(r.nextInt(all.size()));
            words.add(item);
            all.remove(item);
        }
    }

}
