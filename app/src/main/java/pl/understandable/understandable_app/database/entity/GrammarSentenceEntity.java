package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarSentenceEntity extends BaseEntity {

    private String polish;
    private String english;

    public GrammarSentenceEntity(int id, String polish, String english) {
        this.id = id;
        this.polish = polish;
        this.english = english;
    }

    public String getPolish() {
        return polish;
    }

    public String getEnglish() {
        return english;
    }

}
