package pl.understandable.understandable_app.database.entity;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesCategory;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhraseEntity extends BaseEntity {

    private String polish;
    private String english;
    private String category;

    public PhraseEntity(int id, String polish, String english, String category) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
    }

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

    public PhrasesCategory getCategory() {
        return PhrasesCategory.valueOf(category);
    }

}
