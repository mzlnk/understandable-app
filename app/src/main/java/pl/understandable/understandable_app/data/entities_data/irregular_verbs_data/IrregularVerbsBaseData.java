package pl.understandable.understandable_app.data.entities_data.irregular_verbs_data;

import pl.understandable.understandable_app.data.entities_data.BaseData;
import pl.understandable.understandable_app.data.entities_data.Datable;
import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningOrderWay;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_app.database.entity.IrregularVerbEntity;
import pl.understandable.understandable_app.database.repository.IrregularVerbEntityRepository;
import pl.understandable.understandable_app.utils.EntitySortUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin Zielonka on 2017-07-08.
 */

public abstract class IrregularVerbsBaseData extends BaseData implements Datable<IrregularVerbEntity, IrregularVerbsDataParams> {

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
        words = IrregularVerbEntityRepository.getAllEntities(params);
        resize();
        System.out.println("ORDER: " + params.orderWay.name());
        if(params.orderWay.equals(IrregularVerbsLearningOrderWay.ALPHABETICAL)) {
            EntitySortUtil.sort(words);
        }
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
