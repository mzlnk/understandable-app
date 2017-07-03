package net.heliantum.understandable.database.entity;

import net.heliantum.understandable.data.enums.LanguageCategory;
import net.heliantum.understandable.data.enums.LanguageType;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class LanguageEntity extends BaseEntity {

    private String polish;
    private String english;
    private String category;
    private String type;

    public LanguageEntity(int id, String polish, String english, String category, String type) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
        this.type = type;
    }

    public String getPolishWord() {
        return polish;
    }

    public String getEnglishWord() {
        return english;
    }

    public LanguageCategory getCategory() {
        return LanguageCategory.getEnum(category);
    }

    public LanguageType getType() {
        return LanguageType.getEnum(type);
    }

}
