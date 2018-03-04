package pl.understandable.understandable_app.database.entity;

import pl.understandable.understandable_app.data.enums.words.WordsCategory;
import pl.understandable.understandable_app.data.enums.words.WordsSubcategory;
import pl.understandable.understandable_app.data.enums.words.WordsType;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class WordEntity extends BaseWordEntity {

    private String category;
    private String type;
    private String subcategory;

    public WordEntity(int id, String polish, String english, String category, String type, String subcategory, boolean isLearnt) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
        this.type = type;
        this.subcategory = subcategory;
        this.isLearnt = isLearnt;
    }

    public WordsCategory getCategory() {
        return WordsCategory.getEnum(category);
    }

    public WordsType getType() {
        return WordsType.getEnum(type);
    }

    public WordsSubcategory getSubcategory() {
        return WordsSubcategory.valueOf(subcategory);
    }

}
