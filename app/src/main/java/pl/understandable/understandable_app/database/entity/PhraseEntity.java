package pl.understandable.understandable_app.database.entity;

import pl.understandable.understandable_app.data.enums.phrases.PhrasesCategory;

/**
 * Created by Marcin Zielonka on 2017-08-11.
 */

public class PhraseEntity extends BaseWordEntity {

    private String category;

    public PhraseEntity(int id, String polish, String english, String category) {
        this.id = id;
        this.polish = polish;
        this.english = english;
        this.category = category;
    }

    public PhrasesCategory getCategory() {
        return PhrasesCategory.valueOf(category);
    }

}
