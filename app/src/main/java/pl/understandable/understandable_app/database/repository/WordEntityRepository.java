package pl.understandable.understandable_app.database.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import pl.understandable.understandable_app.data.enums.words.WordsLearningWordsWay;
import pl.understandable.understandable_app.data.params.WordsDataParams;
import pl.understandable.understandable_app.database.database_access.DatabaseManager;
import pl.understandable.understandable_app.database.entity.WordEntity;

import java.util.ArrayList;
import java.util.List;

import static pl.understandable.understandable_app.database.database_access.DatabaseManager.database;

/**
 * Created by Marcin Zielonka on 2017-05-06.
 */

public class WordEntityRepository {

    public static WordEntity getEntity(int id) {
        String sql = "SELECT * FROM word_entities WHERE id='" + String.valueOf(id) + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        return new WordEntity(cursor.getInt(0),
                              cursor.getString(1),
                              cursor.getString(2),
                              cursor.getString(3),
                              cursor.getString(4),
                              cursor.getString(5),
                              cursor.getInt(6) == 1);
    }

    public static List<WordEntity> getSpecifiedEntitiesByCategory(WordsDataParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM word_entities WHERE ");
        sb.append("category='").append(params.categories.get(0).name()).append("'");
        if(params.categories.size() > 1) {
            for(int i = 1; i < params.categories.size(); i++) {
                sb.append(" OR ").append("category='").append(params.categories.get(i).name()).append("'");
            }
        }
        String sql = sb.toString();

        List<WordEntity> result = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WordEntity entity = new WordEntity(cursor.getInt(0),
                                               cursor.getString(1),
                                               cursor.getString(2),
                                               cursor.getString(3),
                                               cursor.getString(4),
                                               cursor.getString(5),
                                               cursor.getInt(6) == 1);
            result.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    public static List<WordEntity> getSpecifiedEntitiesBySubcategory(WordsDataParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM word_entities WHERE (");
        sb.append("category='").append(params.categories.get(0).name()).append("'");
        if(params.categories.size() > 1) {
            for(int i = 1; i < params.categories.size(); i++) {
                sb.append(" OR ").append("category='").append(params.categories.get(i).name()).append("'");
            }
        }
        sb.append(")").append("AND (").append("subcategory='").append(params.subcategories.get(0).name()).append("'");
        if(params.subcategories.size() > 1) {
            for(int i = 0; i < params.subcategories.size(); i++) {
                sb.append(" OR ").append("subcategory='").append(params.subcategories.get(i).name()).append("'");
            }
        }
        sb.append(")");
        if(params.wordsWay.equals(WordsLearningWordsWay.NOT_LEARNED)) {
            sb.append(" AND is_learnt=0");
        }
        String sql = sb.toString();

        List<WordEntity> result = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WordEntity entity = new WordEntity(cursor.getInt(0),
                                               cursor.getString(1),
                                               cursor.getString(2),
                                               cursor.getString(3),
                                               cursor.getString(4),
                                               cursor.getString(5),
                                               cursor.getInt(6) == 1);
            result.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    public static List<WordEntity> getSpecifiedEntitiesByType(WordsDataParams params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM word_entities WHERE (");
        sb.append("category='").append(params.categories.get(0).name()).append("'");
        if(params.categories.size() > 1) {
            for(int i = 1; i < params.categories.size(); i++) {
                sb.append(" OR ").append("type='").append(params.types.get(i).name()).append("'");
            }
        }
        sb.append(")").append(" AND (").append("type='").append(params.types.get(0).name()).append("'");
        if(params.types.size() > 1) {
            for(int i = 0; i < params.types.size(); i++) {
                sb.append(" OR ").append("type='").append(params.types.get(i).name()).append("'");
            }
        }
        sb.append(")");
        if(params.wordsWay.equals(WordsLearningWordsWay.NOT_LEARNED)) {
            sb.append(" AND is_learnt=0");
        }
        String sql = sb.toString();

        List<WordEntity> result = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WordEntity entity = new WordEntity(cursor.getInt(0),
                                               cursor.getString(1),
                                               cursor.getString(2),
                                               cursor.getString(3),
                                               cursor.getString(4),
                                               cursor.getString(5),
                                               cursor.getInt(6) == 1);
            result.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }

    public static void updateEntity(WordEntity entity) {
        ContentValues cv = new ContentValues();
        cv.put("is_learnt", entity.isLearnt() ? 1 : 0);
        database.update("word_entities", cv, "id=?", new String[]{String.valueOf(entity.getId())});
    }

}
