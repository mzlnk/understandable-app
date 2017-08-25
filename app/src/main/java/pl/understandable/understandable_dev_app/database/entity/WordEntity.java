package pl.understandable.understandable_dev_app.database.entity;

import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageSubcategory;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageType;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLearningLevel;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class WordEntity extends BaseWordEntity {

    private String category;
    private String type;
    private String level;
    private String subcategory;

    public WordEntity(int id, String polish, String english, String category, String type, String subcategory, String level) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
        this.type = type;
        this.subcategory = subcategory;
        this.level = level;
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

    public WordsLanguageSubcategory getSubcategory() {
        return WordsLanguageSubcategory.valueOf(subcategory);
    }

}
