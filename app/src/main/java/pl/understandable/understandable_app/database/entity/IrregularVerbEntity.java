package pl.understandable.understandable_app.database.entity;

import pl.understandable.understandable_app.database.entity.enums.IrregularVerbEnum;

/**
 * Created by Marcin on 2017-05-06.
 */

public class IrregularVerbEntity extends BaseEntity {

    private String polish;
    private String[] english = new String[3];

    public IrregularVerbEntity(int id, String polish, String... english) {
        this.id = id;
        this.polish = polish;
        this.english = english;
    }

    public String getPolishWord() {
        return polish;
    }

    public String getEnglishWord(IrregularVerbEnum form) {
        return english[form.getPos()];
    }

}