package net.heliantum.ziedic.database.entity;

import net.heliantum.ziedic.data.LanguageCategory;
import net.heliantum.ziedic.data.LanguageType;

/**
 * Created by Marcin Zielonka on 2016-11-11.
 */


public class LanguageEntity {

    private int id;
    private String polish;
    private String english;
    private LanguageCategory category;
    private LanguageType type;

    public LanguageEntity(int id, String polish, String english, LanguageCategory category, LanguageType type) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getPolishWord() {
        return polish;
    }

    public String getEnglishWord() {
        return english;
    }

    public LanguageCategory getCategory() {
        return category;
    }

    public LanguageType getType() {
        return type;
    }

}
