package pl.understandable.understandable_dev_app.database.entity;

import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageCategory;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageSubcategory;
import pl.understandable.understandable_dev_app.data.enums.words.WordsLanguageType;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class WordEntity extends BaseWordEntity {

    private String category;
    private String type;
    private String subcategory;

    public WordEntity(int id, String polish, String english, String category, String type, String subcategory) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
        this.type = type;
        this.subcategory = subcategory;
    }

    public WordsLanguageCategory getCategory() {
        return WordsLanguageCategory.getEnum(category);
    }

    public WordsLanguageType getType() {
        return WordsLanguageType.getEnum(type);
    }

    public WordsLanguageSubcategory getSubcategory() {
        return WordsLanguageSubcategory.valueOf(subcategory);
    }

}
