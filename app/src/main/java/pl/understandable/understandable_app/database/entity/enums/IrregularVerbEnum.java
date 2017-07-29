package pl.understandable.understandable_app.database.entity.enums;

/**
 * Created by Marcin on 2017-05-06.
 */

public enum IrregularVerbEnum {

    INFINITVE(0),
    SIMPLE_PAST(1),
    PAST_PARTICIPLE(2);

    private int pos;

    private IrregularVerbEnum(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

}
