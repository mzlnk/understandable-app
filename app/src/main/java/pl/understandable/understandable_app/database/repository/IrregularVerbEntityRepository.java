package pl.understandable.understandable_app.database.repository;

import android.content.ContentValues;
import android.database.Cursor;

import pl.understandable.understandable_app.data.enums.irregular_verbs.IrregularVerbsLearningWordsWay;
import pl.understandable.understandable_app.data.params.IrregularVerbsDataParams;
import pl.understandable.understandable_app.database.entity.IrregularVerbEntity;

import java.util.ArrayList;
import java.util.List;

import static pl.understandable.understandable_app.database.database_access.DatabaseManager.database;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class IrregularVerbEntityRepository {

    public static List<IrregularVerbEntity> getAllEntities(IrregularVerbsDataParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM irregular_verb_entities");
        if(params.wordsWay.equals(IrregularVerbsLearningWordsWay.NOT_LEARNED)) {
            sb.append(" WHERE is_learnt=0");
        }
        String sql = sb.toString();

        List<IrregularVerbEntity> result = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            IrregularVerbEntity entity = new IrregularVerbEntity(cursor.getInt(0),
                    cursor.getString(1),
                    new String[] {cursor.getString(2), cursor.getString(3), cursor.getString(4)},
                    cursor.getInt(5) == 1);
            result.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    public static void updateEntity(IrregularVerbEntity entity) {
        ContentValues cv = new ContentValues();
        cv.put("is_learnt", entity.isLearnt() ? 1 : 0);
        database.update("irregular_verb_entities", cv, "id=?", new String[]{String.valueOf(entity.getId())});
    }


}
