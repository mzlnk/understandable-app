package pl.understandable.understandable_app.database.entity;

import pl.understandable.understandable_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_app.data.enums.words.WordsLanguageType;
import pl.understandable.understandable_app.data.enums.words.WordsLearningLevel;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class WordEntity extends BaseEntity {

    private String polish;
    private String english;
    private String category;
    private String type;
    private String level;

    public WordEntity(int id, String polish, String english, String category, String type, String level) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
        this.type = type;
        this.level = level;
    }

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

    public WordsLanguageCategory getCategory() {
        return WordsLanguageCategory.getEnum(category);
    }

    public WordsLanguageType getType() {
        return WordsLanguageType.getEnum(type);
    }

    public WordsLearningLevel getLevel() {
        return WordsLearningLevel.valueOf(level);
    }

}
