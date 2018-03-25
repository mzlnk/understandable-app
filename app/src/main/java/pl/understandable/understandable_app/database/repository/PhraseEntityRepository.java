package pl.understandable.understandable_app.database.repository;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pl.understandable.understandable_app.data.enums.words.WordsLearningWordsWay;
import pl.understandable.understandable_app.data.params.PhrasesDataParams;
import pl.understandable.understandable_app.database.entity.PhraseEntity;
import pl.understandable.understandable_app.database.entity.WordEntity;

import static pl.understandable.understandable_app.database.database_access.DatabaseManager.database;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhraseEntityRepository {

    public static List<PhraseEntity> getSpecifiedEntitiesByCategory(PhrasesDataParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM phrase_entities WHERE (");
        sb.append("category='").append(params.category).append("'");
        sb.append(")");
        if(params.wordsWay.equals(WordsLearningWordsWay.NOT_LEARNED)) {
            sb.append(" AND is_learnt=0");
        }
        String sql = sb.toString();

        List<PhraseEntity> result = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PhraseEntity entity = new PhraseEntity(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 1);
            result.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
    public static void updateEntity(PhraseEntity entity) {
        ContentValues cv = new ContentValues();
        cv.put("is_learnt", entity.isLearnt() ? 1 : 0);
        database.update("phrase_entities", cv, "id=?", new String[]{String.valueOf(entity.getId())});
    }

}
