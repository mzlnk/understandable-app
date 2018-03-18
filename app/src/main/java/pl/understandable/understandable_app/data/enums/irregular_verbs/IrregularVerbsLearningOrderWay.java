package pl.understandable.understandable_app.data.enums.irregular_verbs;

import pl.understandable.understandable_app.R;
import pl.understandable.understandable_app.data.enums.Identifiable;

/**
 * Created by Marcin Zielonka on 2018-03-05.
 */

public enum IrregularVerbsLearningOrderWay implements Identifiable {

    ALPHABETICAL("ułóż alfabetycznie", R.drawable.f_words_choice_way_alphabetical_order),
    NO_ORDER("", 0);

    private String name;
    private int resId;

    private IrregularVerbsLearningOrderWay(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getResId() {
        return resId;
    }


}
