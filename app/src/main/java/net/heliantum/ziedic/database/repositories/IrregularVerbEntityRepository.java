package net.heliantum.ziedic.database.repositories;

import android.content.Context;

import net.heliantum.ziedic.database.entity.IrregularVerbEntity;
import net.heliantum.ziedic.database.handlers.IrregularVerbEntityDBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-05-06.
 */

public class IrregularVerbEntityRepository extends BaseEntityRepository {

    private List<IrregularVerbEntity> irregularVerbEntities = new ArrayList<>();

    private static IrregularVerbEntityRepository repository;

    public static void init(Context context) {
        repository = new IrregularVerbEntityRepository();
        repository.loadEntitiesFromDatabase(context);
    }

    public static void reload(Context context) {
        repository.irregularVerbEntities.clear();
        init(context);
    }

    public static IrregularVerbEntityRepository getRepository() {
        return repository;
    }

    public List<IrregularVerbEntity> getAllEntities() {
        return new ArrayList<>(irregularVerbEntities);
    }

    private void loadEntitiesFromDatabase(Context context) {
        IrregularVerbEntityDBHandler handler = new IrregularVerbEntityDBHandler(context);
        irregularVerbEntities = handler.getData();
    }

}
