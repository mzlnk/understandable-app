package net.heliantum.ziedic.data;

import net.heliantum.ziedic.database.entity.LanguageEntity;
import net.heliantum.ziedic.database.repository.LanguageEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marcin on 2017-05-06.
 */

public abstract class BaseData {

    protected static final Random r = new Random();

    protected DataParams params;
    protected List<LanguageEntity> words = new ArrayList<>();

    public BaseData(DataParams params) {
        this.params = params;
        generateWords();
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
