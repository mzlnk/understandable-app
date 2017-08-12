package pl.understandable.understandable_app.database.entity;

/**
 * Created by Marcin on 2017-08-12.
 */

public class GrammarFillGapEntity extends BaseEntity {

    private String sentence;
    private String gap;

    public GrammarFillGapEntity(int id, String english, String gap) {
        this.id = id;
        this.sentence = english;
        this.gap = gap;
    }

    public String getSentence() {
        return sentence;
    }

    public String getGap() {
        return gap;
    }

}
